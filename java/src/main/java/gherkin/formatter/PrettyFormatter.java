package gherkin.formatter;

import gherkin.formatter.model.*;
import gherkin.util.Mapper;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

import static gherkin.util.FixJava.join;
import static gherkin.util.FixJava.map;

/**
 * This class pretty prints feature files like they were in the source, only
 * prettier. That is, with consistent indentation. This class is also a {@link Formatter},
 * which means it can be used to print execution results - highlighting arguments,
 * printing source information and exception information.
 */
public class PrettyFormatter implements Reporter {
    private final StepPrinter stepPrinter = new StepPrinter();
    private final PrintWriter out;
    private final boolean monochrome;
    private final boolean executing;

    private String uri;
    private Mapper tagNameMapper = new Mapper() {
        public String map(Object tag) {
            return ((Tag) tag).getName();
        }
    };
    private Formats formats;
    private Match match;
    private int[][] cellLengths;
    private int[] maxLengths;
    private int rowIndex;
    private List<Row> rows;
    private Integer rowHeight = null;

    private List<Step> steps = new ArrayList<Step>();
    private List<Integer> indentations = new ArrayList<Integer>();
    private DescribedStatement statement;
    private int exampleResultCount;
    private List<CellResult> cellResults;
    private Integer stepCount;
    private boolean inOutline;

    public PrettyFormatter(Writer out, boolean monochrome, boolean executing) {
        this.out = new PrintWriter(out);
        this.monochrome = monochrome;
        this.executing = executing;
        setFormats(monochrome);
    }

    public PrettyFormatter(OutputStream out, boolean monochrome, boolean executing) {
        this(new OutputStreamWriter(out, Charset.forName("UTF-8")), monochrome, executing);
    }

    private void setFormats(boolean monochrome) {
        if (monochrome) {
            formats = new MonochromeFormats();
        } else {
            formats = new AnsiFormats();
        }
    }

    public void uri(String uri) {
        this.uri = uri;
    }

    public void feature(Feature feature) {
        printComments(feature.getComments(), "");
        printTags(feature.getTags(), "");
        out.println(feature.getKeyword() + ": " + feature.getName());
        printDescription(feature.getDescription(), "  ", false);
        out.flush();
    }

    public void background(Background background) {
        statement = background;
        inOutline = false;
    }

    public void scenario(Scenario scenario) {
        replay();
        statement = scenario;
        inOutline = false;
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        replay();
        statement = scenarioOutline;
        inOutline = true;
    }

    private void replay() {
        printStatement();
        printSteps();
    }

    private void printSteps() {
        while (!steps.isEmpty()) {
            printStep("skipped", null, null, true);
        }
    }

    private void printStatement() {
        if (statement == null) {
            return;
        }
        calculateLocationIndentations();
        out.println();
        printComments(statement.getComments(), "  ");
        printTags(statement.getTags(), "  ");
        out.print("  ");
        out.print(statement.getKeyword());
        out.print(": ");
        out.print(statement.getName());
        String location = executing ? uri + ":" + statement.getLine() : null;
        out.println(indentedLocation(location, true));
        printDescription(statement.getDescription(), "    ", true);
        out.flush();
        statement = null;
    }

    private String indentedLocation(String location, boolean proceed) {
        StringBuffer sb = new StringBuffer();
        int indentation = proceed ? indentations.remove(0) : indentations.get(0);
        if (location == null) {
            return "";
        }
        for (int i = 0; i < indentation; i++) {
            sb.append(' ');
        }
        sb.append(' ');
        sb.append(getFormat("comment").text("# " + location));
        return sb.toString();
    }

    public void examples(Examples examples) {
        replay();
        out.println();
        printComments(examples.getComments(), "    ");
        printTags(examples.getTags(), "    ");
        out.print("    ");
        out.print(examples.getKeyword());
        out.print(": ");
        out.print(examples.getName());
        out.println();
        printDescription(examples.getDescription(), "      ", true);
        table(examples.getRows());

        rowIndex = 0;
        printExampleRow(examples.getRows().get(0).createResults("skipped_arg"));
    }

    public void step(Step step) {
        if (steps.isEmpty()) {
            stepCount = null;
        }
        steps.add(step);
    }

    public void match(Match match) {
        this.match = match;
        if (match.getMatchedColumns() == null) {
            printStatement();
            if (!monochrome) {
            }
        } else {
            // Examples
            if (isNewRow()) {
                rowIndex++;
                cellResults = rows.get(rowIndex).createResults("executing");
                rowHeight = null;
            }
            printExampleRow(cellResults);
        }
    }

    private boolean isNewRow() {
        return exampleResultCount % stepCount == 0;
    }

    public void result(Result result) {
        if (match.getMatchedColumns() == null) {
            printStep(result);
        } else {
            printExampleRow(result);
        }
    }

    private void printExampleRow(Result result) {
        for (Integer matchedColumn : match.getMatchedColumns()) {
            cellResults.get(matchedColumn).addResult(result);
        }
        printExampleRow(cellResults);
        printExampleErrors(cellResults, result);
        exampleResultCount++;
    }

    private void printStep(Result result) {
        if (!monochrome) {
            out.print(formats.up(1)); // TODO: keep track of height
        }
        printStep(result.getStatus(), match.getArguments(), match.getLocation(), true);
        if (result.getErrorMessage() != null) {
            printError(result);
        }
    }

    private void printStep(String status, List<Argument> arguments, String location, boolean proceed) {
        if (stepCount == null) {
            stepCount = steps.size();
        }
        Step step = proceed ? steps.remove(0) : steps.get(0);

        if (arguments == null) {
            arguments = (inOutline) ? step.getOutlineArgs() : Collections.<Argument>emptyList();
        }

        Format textFormat = getFormat(status);
        Format argFormat = getArgFormat(status);

        printComments(step.getComments(), "    ");
        out.print("    ");
        out.print(textFormat.text(step.getKeyword()));
        stepPrinter.writeStep(out, textFormat, argFormat, step.getName(), arguments);
        out.print(indentedLocation(location, proceed));

        out.println();
        if (step.getRows() != null) {
            table(step.getRows());
        } else if (step.getPyString() != null) {
            pyString(step.getPyString());
        }

        out.flush();
    }

    private Format getFormat(String key) {
        return formats.get(key);
    }

    private Format getArgFormat(String key) {
        return formats.get(key + "_arg");
    }

    public void table(List<Row> rows) {
        prepareTable(rows);
        if (!executing) {
            for (Row row : rows) {
                printExampleRow(this.rows.get(0).createResults("executing"));
            }
        }
    }

    private void prepareTable(List<Row> rows) {
        this.rows = rows;
        int columnCount = rows.get(0).getCells().size();
        cellLengths = new int[rows.size()][columnCount];
        maxLengths = new int[columnCount];
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                Row row = rows.get(rowIndex);
                int length = escapeCell(row.getCells().get(colIndex)).length();
                cellLengths[rowIndex][colIndex] = length;
                maxLengths[colIndex] = Math.max(maxLengths[colIndex], length);
            }
        }
    }

    private void printExampleRow(List<CellResult> cellResults) {
        Row row = rows.get(rowIndex);
        if (!isNewRow() || rowHeight != null) {
            out.print(formats.up(rowHeight));
        }
        rowHeight = 0;

        for (Comment comment : row.getComments()) {
            out.write("      ");
            out.println(comment.getValue());
            rowHeight++;
        }
        out.write("      | ");
        for (int colIndex = 0; colIndex < maxLengths.length; colIndex++) {
            String cellText = escapeCell(row.getCells().get(colIndex));
            CellResult cellResult = cellResults.get(colIndex);
            String status = cellResult.getStatus();
            Format format = formats.get(status);
            out.write(format.text(cellText));
            int padding = maxLengths[colIndex] - cellLengths[rowIndex][colIndex];
            padSpace(padding);
            if (colIndex < maxLengths.length - 1) {
                out.write(" | ");
            } else {
                out.write(" |");
            }
        }
        out.println();
        rowHeight++;
    }

    private void printExampleErrors(List<CellResult> cellResults, Result result) {
        Set<Result> seenResults = new HashSet<Result>();
        for (CellResult cellResult : cellResults) {
            for (Result r : cellResult.getResults()) {
                printResultError(seenResults, r);
            }
        }
        printResultError(seenResults, result);
        out.flush();
    }

    private void printResultError(Set<Result> seenResults, Result result) {
        if (result.getErrorMessage() != null && !seenResults.contains(result)) {
            printError(result);
            rowHeight += result.getErrorMessage().split("\n").length;
            seenResults.add(result);
        }
    }

    private void printError(Result result) {
        Format failed = formats.get("failed");
        out.println(indent(failed.text(result.getErrorMessage()), "      "));
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException();
    }

    private String escapeCell(String cell) {
        return cell.replaceAll("\\\\(?!\\|)", "\\\\\\\\").replaceAll("\\n", "\\\\n").replaceAll("\\|", "\\\\|");
    }

    public void pyString(PyString pyString) {
        out.println("      \"\"\"");
        out.print(escapeTripleQuotes(indent(pyString.getValue(), "      ")));
        out.println("\n      \"\"\"");
    }

    public void eof() {
        replay();
        out.flush();
    }

    private void calculateLocationIndentations() {
        int[] lineWidths = new int[steps.size() + 1];
        int i = 0;

        List<BasicStatement> statements = new ArrayList<BasicStatement>();
        statements.add(statement);
        statements.addAll(steps);
        int maxLineWidth = 0;
        for (BasicStatement statement : statements) {
            int stepWidth = statement.getKeyword().length() + statement.getName().length();
            lineWidths[i++] = stepWidth;
            maxLineWidth = Math.max(maxLineWidth, stepWidth);
        }
        for (int lineWidth : lineWidths) {
            indentations.add(maxLineWidth - lineWidth);
        }
    }

    public void embedding(String mimeType, byte[] data) {
    }

    private void padSpace(int indent) {
        for (int i = 0; i < indent; i++) {
            out.write(" ");
        }
    }

    private void printComments(List<Comment> comments, String indent) {
        for (Comment comment : comments) {
            out.print(indent);
            out.println(comment.getValue());
        }
        out.flush();
    }

    private void printTags(List<Tag> tags, String indent) {
        if (tags.isEmpty()) return;
        out.print(indent);
        out.println(join(map(tags, tagNameMapper), " "));
        out.flush();
    }

    private void printDescription(String description, String indentation, boolean newline) {
        if (!"".equals(description)) {
            out.println(indent(description, indentation));
            if (newline) out.println();
        }
    }

    private static final Pattern START = Pattern.compile("^", Pattern.MULTILINE);

    private static String indent(String s, String indentation) {
        return START.matcher(s).replaceAll(indentation);
    }

    private static final Pattern TRIPLE_QUOTES = Pattern.compile("\"\"\"", Pattern.MULTILINE);
    private static final String ESCAPED_TRIPLE_QUOTES = "\\\\\"\\\\\"\\\\\"";

    private static String escapeTripleQuotes(String s) {
        return TRIPLE_QUOTES.matcher(s).replaceAll(ESCAPED_TRIPLE_QUOTES);
    }
}