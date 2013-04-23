package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.BasicStatement;
import gherkin.formatter.model.CellResult;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DescribedStatement;
import gherkin.formatter.model.DocString;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import gherkin.formatter.model.TagStatement;
import gherkin.util.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static gherkin.util.FixJava.join;
import static gherkin.util.FixJava.map;

/**
 * This class pretty prints feature files like they were in the source, only
 * prettier. That is, with consistent indentation. This class is also a {@link Reporter},
 * which means it can be used to print execution results - highlighting arguments,
 * printing source information and exception information.
 */
public class PrettyFormatter implements Reporter, Formatter {
    private final StepPrinter stepPrinter = new StepPrinter();
    private final NiceAppendable out;
    private boolean monochrome;
    private final boolean executing;

    private String uri;
    private Mapper<Tag, String> tagNameMapper = new Mapper<Tag, String>() {
        @Override
        public String map(Tag tag) {
            return tag.getName();
        }
    };
    private Formats formats;
    private Match match;
    private int[][] cellLengths;
    private int[] maxLengths;
    private int rowIndex;
    private List<? extends Row> rows;
    private Integer rowHeight = null;
    private boolean rowsAbove = false;

    private List<Step> steps = new ArrayList<Step>();
    private List<Integer> indentations = new ArrayList<Integer>();
    private DescribedStatement statement;

    public PrettyFormatter(Appendable out, boolean monochrome, boolean executing) {
        this.out = new NiceAppendable(out);
        this.executing = executing;
        setMonochrome(monochrome);
    }

    public void setMonochrome(boolean monochrome) {
        this.monochrome = monochrome;
        if (monochrome) {
            formats = new MonochromeFormats();
        } else {
            formats = new AnsiFormats();
        }
    }

    @Override
    public void uri(String uri) {
        this.uri = uri;
    }

    @Override
    public void feature(Feature feature) {
        printComments(feature.getComments(), "");
        printTags(feature.getTags(), "");
        out.println(feature.getKeyword() + ": " + feature.getName());
        printDescription(feature.getDescription(), "  ", false);
    }

    @Override
    public void background(Background background) {
        replay();
        statement = background;
    }

    @Override
    public void scenario(Scenario scenario) {
        replay();
        statement = scenario;
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        replay();
        statement = scenarioOutline;
    }

    private void replay() {
        printStatement();
        printSteps();
    }

    private void printSteps() {
        while (!steps.isEmpty()) {
            printStep("skipped", Collections.<Argument>emptyList(), null, true);
        }
    }

    private void printStatement() {
        if (statement == null) {
            return;
        }
        calculateLocationIndentations();
        out.println();
        printComments(statement.getComments(), "  ");
        if (statement instanceof TagStatement) {
            printTags(((TagStatement) statement).getTags(), "  ");
        }
        out.append("  ");
        out.append(statement.getKeyword());
        out.append(": ");
        out.append(statement.getName());
        String location = executing ? uri + ":" + statement.getLine() : null;
        out.println(indentedLocation(location, true));
        printDescription(statement.getDescription(), "    ", true);
        statement = null;
    }

    private String indentedLocation(String location, boolean proceed) {
        StringBuilder sb = new StringBuilder();
        int indentation = 0;
        if (indentations.size() > 0) {
            indentation = proceed ? indentations.remove(0) : indentations.get(0);
        }

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

    @Override
    public void examples(Examples examples) {
        replay();
        out.println();
        printComments(examples.getComments(), "    ");
        printTags(examples.getTags(), "    ");
        out.append("    ");
        out.append(examples.getKeyword());
        out.append(": ");
        out.append(examples.getName());
        out.println();
        printDescription(examples.getDescription(), "      ", true);
        table(examples.getRows());
    }

    @Override
    public void step(Step step) {
        steps.add(step);
    }

    @Override
    public void match(Match match) {
        this.match = match;
        printStatement();
        if (!monochrome) {
            printStep("executing", match.getArguments(), match.getLocation(), false);
        }
    }

    @Override
    public void embedding(String mimeType, byte[] data) {
        // Do nothing
    }

    @Override
    public void write(String text) {
        out.println(getFormat("output").text(text));
    }

    @Override
    public void result(Result result) {
        if (!monochrome) {
            out.append(formats.up(1));
        }
        if (match != null) {
            printStep(result.getStatus(), match.getArguments(), match.getLocation(), true);
        }
        //Match should only be null if there's an error in something that's not a step, so this should be safe
        if (result.getErrorMessage() != null) {
            printError(result);
        }
    }

    @Override
    public void before(Match match, Result result) {
        printHookFailure(match, result, true);
    }

    @Override
    public void after(Match match, Result result) {
        printHookFailure(match, result, false);
    }

    private void printHookFailure(Match match, Result result, boolean isBefore) {
        if (result.getStatus().equals(Result.FAILED)) {
            if (!monochrome) {
                out.append(formats.up(1));
            }

            Format format = getFormat(result.getStatus());

            StringBuffer context = new StringBuffer("Failure in ");
            if (isBefore) {
                context.append("before");
            } else {
                context.append("after");
            }
            context.append(" hook:");

            out.append(format.text(context.toString()));
            out.append(format.text(match.getLocation()));
            out.println();
            out.append(format.text("Message: "));
            out.append(format.text(result.getErrorMessage()));

            if (result.getError() != null) {
                printError(result);
            }
        }

    }

    private void printStep(String status, List<Argument> arguments, String location, boolean proceed) {
        Step step = proceed ? steps.remove(0) : steps.get(0);
        Format textFormat = getFormat(status);
        Format argFormat = getArgFormat(status);

        printComments(step.getComments(), "    ");
        out.append("    ");
        out.append(textFormat.text(step.getKeyword()));
        stepPrinter.writeStep(out, textFormat, argFormat, step.getName(), arguments);
        out.append(indentedLocation(location, proceed));

        out.println();
        if (step.getRows() != null) {
            table(step.getRows());
        } else if (step.getDocString() != null) {
            docString(step.getDocString());
        }
    }

    private Format getFormat(String key) {
        return formats.get(key);
    }

    private Format getArgFormat(String key) {
        return formats.get(key + "_arg");
    }

    public void table(List<? extends Row> rows) {
        prepareTable(rows);
        if (!executing) {
            for (Row row : rows) {
                row(row.createResults("skipped"));
                nextRow();
            }
        }
    }

    private void prepareTable(List<? extends Row> rows) {
        this.rows = rows;

        // find the largest row
        int columnCount = 0;
        for (Row row : rows) {
            if (columnCount < row.getCells().size()) {
                columnCount = row.getCells().size();
            }
        }

        cellLengths = new int[rows.size()][columnCount];
        maxLengths = new int[columnCount];
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            Row row = rows.get(rowIndex);
            final List<String> cells = row.getCells();
            for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                final String cell = getCellSafely(cells, colIndex);
                final int length = escapeCell(cell).length();
                cellLengths[rowIndex][colIndex] = length;
                maxLengths[colIndex] = Math.max(maxLengths[colIndex], length);
            }
        }
        rowIndex = 0;
    }

    private String getCellSafely(final List<String> cells, final int colIndex) {
        return (colIndex < cells.size()) ? cells.get(colIndex) : "";
    }

    public void row(List<CellResult> cellResults) {
        Row row = rows.get(rowIndex);
        if (rowsAbove) {
            out.append(formats.up(rowHeight));
        } else {
            rowsAbove = true;
        }
        rowHeight = 1;

        for (Comment comment : row.getComments()) {
            out.append("      ");
            out.println(comment.getValue());
            rowHeight++;
        }
        switch (row.getDiffType()) {
            case NONE:
                out.append("      | ");
                break;
            case DELETE:
                out.append("    ").append(formats.get("skipped").text("-")).append(" | ");
                break;
            case INSERT:
                out.append("    ").append(formats.get("comment").text("+")).append(" | ");
                break;
        }
        for (int colIndex = 0; colIndex < maxLengths.length; colIndex++) {
            String cellText = escapeCell(getCellSafely(row.getCells(), colIndex));
            String status = null;
            switch (row.getDiffType()) {
                case NONE:
                    status = cellResults.size() < colIndex ? cellResults.get(colIndex).getStatus() : "skipped";
                    break;
                case DELETE:
                    status = "skipped";
                    break;
                case INSERT:
                    status = "comment";
                    break;
            }
            Format format = formats.get(status);
            out.append(format.text(cellText));
            int padding = maxLengths[colIndex] - cellLengths[rowIndex][colIndex];
            padSpace(padding);
            if (colIndex < maxLengths.length - 1) {
                out.append(" | ");
            } else {
                out.append(" |");
            }
        }
        out.println();
        rowHeight++;
        Set<Result> seenResults = new HashSet<Result>();
        for (CellResult cellResult : cellResults) {
            for (Result result : cellResult.getResults()) {
                if (result.getErrorMessage() != null && !seenResults.contains(result)) {
                    printError(result);
                    rowHeight += result.getErrorMessage().split("\n").length;
                    seenResults.add(result);
                }
            }
        }
    }

    private void printError(Result result) {
        Format failed = formats.get("failed");
        out.println(indent(failed.text(result.getErrorMessage()), "      "));
    }

    public void nextRow() {
        rowIndex++;
        rowsAbove = false;
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void done() {
        // We're *not* closing the stream here.
        // https://github.com/cucumber/gherkin/issues/151
        // https://github.com/cucumber/cucumber-jvm/issues/96
    }

    @Override
    public void close() {
        out.close();
    }

    private String escapeCell(String cell) {
        return cell.replaceAll("\\\\(?!\\|)", "\\\\\\\\").replaceAll("\\n", "\\\\n").replaceAll("\\|", "\\\\|");
    }

    public void docString(DocString docString) {
        out.println("      \"\"\"");
        out.append(escapeTripleQuotes(indent(docString.getValue(), "      ")));
        out.println("\n      \"\"\"");
    }

    public void eof() {
        replay();
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

    private void padSpace(int indent) {
        for (int i = 0; i < indent; i++) {
            out.append(" ");
        }
    }

    private void printComments(List<Comment> comments, String indent) {
        for (Comment comment : comments) {
            out.append(indent);
            out.println(comment.getValue());
        }
    }

    private void printTags(List<Tag> tags, String indent) {
        if (tags.isEmpty()) return;
        out.append(indent);
        out.println(join(map(tags, tagNameMapper), " "));
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
