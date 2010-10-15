package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.PyString;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import gherkin.formatter.model.TagStatement;
import gherkin.util.Mapper;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.regex.Pattern;

import static gherkin.util.FixJava.join;
import static gherkin.util.FixJava.map;

/**
 * This class pretty prints feature files like they were in the source, only
 * prettier. That is, with consistent indentation. This class is also a {@link Formatter},
 * which means it can be used to print execution results - highlighting arguments,
 * printing source information and exception information.
 */
public class PrettyFormatter implements Formatter {
    private final PrintWriter out;
    private int maxStepLength = -1;
    private int[] stepLengths;
    private int stepIndex;
    private String uri;
    private Mapper tagNameMapper = new Mapper() {
        public String map(Object tag) {
            return ((Tag) tag).getName();
        }
    };
    private final StepPrinter stepPrinter = new StepPrinter();
    private Formats formats;

    public PrettyFormatter(Writer out, boolean monochrome) {
        this.out = new PrintWriter(out);
        setFormats(monochrome);
    }

    private void setFormats(boolean monochrome) {
        if (monochrome) {
            formats = new MonochromeFormats();
        } else {
            try {
                formats = colorFormats();
            } catch (Exception e) {
                formats = new MonochromeFormats();
            }
        }
    }

    private Formats colorFormats() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Formats) getClass().getClassLoader().loadClass("gherkin.formatter.JansiFormats").newInstance();
    }

    public PrettyFormatter(OutputStream out) {
        this(new OutputStreamWriter(out), false);
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

    public void scenario(Scenario statement) {
        printTagStatement(statement);
    }

    public void scenarioOutline(ScenarioOutline statement) {
        printTagStatement(statement);
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
        printStep(step);
        if (step.getRows() != null) {
            table(step.getRows());
        } else if (step.getPyString() != null) {
            pyString(step.getPyString());
        }
    }

    private void printStep(Step step) {
        printComments(step.getComments(), "    ");
        out.print("    ");
        getTextFormat(step).writeText(out, step.getKeyword());
        stepPrinter.writeStep(out, getTextFormat(step), getArgFormat(step), step.getName(), step.getArguments());

        Result result = step.getResult();
        String location = result != null ? result.getStepdefLocation() : null;
        printIndentedStepLocation(location);
        out.println();
        if (step.getResult() != null && step.getResult().getErrorMessage() != null) {
            out.println(indent(step.getResult().getErrorMessage(), "      "));
        }
        out.flush();
    }

    private Format getTextFormat(Step step) {
        return getFormat(step.getStatus());
    }

    private Format getArgFormat(Step step) {
        return getFormat(step.getStatus() + "_param");
    }

    private Format getFormat(String key) {
        return formats.get(key);
    }

    public void table(List<Row> rows) {
        int columnCount = rows.get(0).getCells().size();
        int[][] cellLengths = new int[rows.size()][columnCount];
        int[] maxLengths = new int[columnCount];
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < columnCount; j++) {
                int length = escapeCell(rows.get(i).getCells().get(j)).length();
                cellLengths[i][j] = length;
                maxLengths[j] = Math.max(maxLengths[j], length);
            }
        }

        for (int i = 0; i < rows.size(); i++) {
            for (Comment comment : rows.get(i).getComments()) {
                out.write("      ");
                out.println(comment.getValue());
            }
            out.write("      | ");
            for (int j = 0; j < columnCount; j++) {
                out.write(escapeCell(rows.get(i).getCells().get(j)));
                padSpace(maxLengths[j] - cellLengths[i][j]);
                if (j < columnCount - 1) {
                    out.write(" | ");
                } else {
                    out.write(" |");
                }
            }
            out.println();
        }
        out.flush();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException();
    }

    private String escapeCell(String cell) {
        return cell.replaceAll("\\|", "\\\\|").replaceAll("\\\\(?!\\|)", "\\\\\\\\");
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
        getFormat("comment").writeText(out, "# " + uri + ":" + line);
    }

    private void printIndentedStepLocation(String location) {
        if (location == null || "".equals(location)) return;
        int indent = maxStepLength - stepLengths[stepIndex += 1];

        padSpace(indent);
        out.append(" ");
        getFormat("comment").writeText(out, "# " + location);
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