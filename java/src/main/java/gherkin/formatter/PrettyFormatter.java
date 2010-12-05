package gherkin.formatter;

import gherkin.formatter.model.*;
import gherkin.util.Mapper;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private int maxStepLength = -1;
    private int[] stepLengths;
    private int stepIndex;
    private String uri;
    private Mapper tagNameMapper = new Mapper() {
        public String map(Object tag) {
            return ((Tag) tag).getName();
        }
    };
    private Formats formats;
    private Step step;
    private Match match;
    private int[][] cellLengths;
    private int[] maxLengths;
    private int rowIndex;
    private List<Row> rows;
    private int rowPrintCount = 0;

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
            try {
                formats = colorFormats();
            } catch (Throwable e) {
                System.err.println("WARNING: Couldn't load color formatter: " + e.getMessage());
                formats = new MonochromeFormats();
            }
        }
    }

    private Formats colorFormats() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Formats) getClass().getClassLoader().loadClass("gherkin.formatter.JansiFormats").newInstance();
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
        out.println();
        printComments(background.getComments(), "  ");
        out.println("  " + background.getKeyword() + ": " + background.getName());
        printDescription(background.getDescription(), "    ", true);
    }

    public void scenario(Scenario scenario) {
        printTagStatement(scenario);
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        printTagStatement(scenarioOutline);
    }

    private void printTagStatement(TagStatement statement) {
        out.println();
        printComments(statement.getComments(), "  ");
        printTags(statement.getTags(), "  ");
        out.print("  ");
        out.print(statement.getKeyword());
        out.print(": ");
        out.print(statement.getName());
        printIndentedScenarioLocation(statement.getKeyword(), statement.getName(), statement.getLine());
        out.println();
        printDescription(statement.getDescription(), "    ", true);
        out.flush();
    }

    public void examples(Examples examples) {
        out.println();
        printComments(examples.getComments(), "    ");
        printTags(examples.getTags(), "    ");
        out.print("    ");
        out.print(examples.getKeyword());
        out.print(": ");
        out.print(examples.getName());
        out.println();
        printDescription(examples.getDescription(), "    ", true);
        table(examples.getRows());
    }

    public void step(Step step) {
        this.step = step;
        stepIndex ++;
        if(!executing) {
            match(Match.NONE);
            result(Result.SKIPPED);
        }
    }

    public void match(Match match) {
        this.match = match;
        if(!monochrome) {
            printStep("executing", match.getArguments(), match.getLocation());
        }
    }

    public void result(Result result) {
        if(!monochrome) {
            out.print(formats.up(1));
        }
        printStep(result.getStatus(), match.getArguments(), match.getLocation());

        if (result.getErrorMessage() != null) {
            out.println(indent(result.getErrorMessage(), "      "));
        }
    }

    private void printStep(String status, List<Argument> arguments, String location) {
        Format textFormat = getFormat(status);
        Format argFormat = getArgFormat(status);

        printComments(step.getComments(), "    ");
        out.print("    ");
        out.print(textFormat.text(step.getKeyword()));
        stepPrinter.writeStep(out, textFormat, argFormat, step.getName(), arguments);
        printIndentedStepLocation(location);

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
        if(!executing) {
            for (Row row : rows) {
                row(row.createResults("skipped"));
                nextRow();
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
                int length = escapeCell(rows.get(rowIndex).getCells().get(colIndex)).length();
                cellLengths[rowIndex][colIndex] = length;
                maxLengths[colIndex] = Math.max(maxLengths[colIndex], length);
            }
        }
        rowIndex = 0;
    }

    public void row(List<CellResult> cellResults) {
        Row row = rows.get(rowIndex);
        if(rowPrintCount > 0) {
            // If we already printed this row, move the cursor
            out.print(formats.up(row.getComments().size() + 1));
        }

        for (Comment comment : row.getComments()) {
            out.write("      ");
            out.println(comment.getValue());
        }
        out.write("      | ");
        for (int colIndex = 0; colIndex < maxLengths.length; colIndex++) {
            String cellText = escapeCell(row.getCells().get(colIndex));
            String status = cellResults.get(colIndex).getStatus();
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
        Set<Result> seenResults = new HashSet<Result>();
        for (CellResult cellResult : cellResults) {
            for (Result result : cellResult.getResults()) {
                if(result.getErrorMessage() != null && !seenResults.contains(result)) {
                    out.println(indent(result.getErrorMessage(), "      "));
                    seenResults.add(result);
                }
            }
        }

        out.flush();
        rowPrintCount++;
    }

    public void nextRow() {
        rowIndex++;
        rowPrintCount=0;
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
        out.flush();
    }

    public void steps(List<Step> steps) {
        stepLengths = new int[steps.size()];
        int i = 0;
        for (Step step : steps) {
            int stepLength = step.getKeyword().length() + step.getName().length();
            stepLengths[i++] = stepLength;
            maxStepLength = Math.max(maxStepLength, stepLength);
        }
        stepIndex = -1;
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

    private void printIndentedScenarioLocation(String keyword, String name, long line) {
        if (maxStepLength == -1) return;
        int l = keyword.length() + name.length();
        maxStepLength = Math.max(maxStepLength, l);
        int indent = maxStepLength - l;

        padSpace(indent);
        out.append(" ");
        out.print(getFormat("comment").text("# " + uri + ":" + line));
    }

    private void printIndentedStepLocation(String location) {
        if (location == null || "".equals(location)) return;
        int indent = maxStepLength - stepLengths[stepIndex];

        padSpace(indent);
        out.append(" ");
        out.print(getFormat("comment").text("# " + location));
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