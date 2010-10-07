package gherkin.formatter;

import gherkin.formatter.model.*;
import gherkin.util.Mapper;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

import static gherkin.formatter.Colors.comments;
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
    private final boolean monochrome;
    private int maxStepLength = -1;
    private int[] stepLengths;
    private int stepIndex;
    private String uri;
    private static final Pattern START = Pattern.compile("^", Pattern.MULTILINE);
    private static final Pattern TRIPLE_QUOTES = Pattern.compile("\"\"\"", Pattern.MULTILINE);
    private Mapper tagNameMapper = new Mapper() {
        public String map(Object tag) {
            return ((Tag) tag).getName();
        }
    };

    public PrettyFormatter(Writer out, boolean monochrome) {
        this.out = new PrintWriter(out);
        this.monochrome = monochrome;
    }

    public PrettyFormatter(OutputStream out, boolean monochrome) throws UnsupportedEncodingException {
        this(new OutputStreamWriter(out, "UTF-8"), monochrome);
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
        out.println("  " + statement.getKeyword() + ": " + statement.getName() + indentedScenarioLocation(statement.getKeyword(), statement.getName(), statement.getLine()));
        printDescription(statement.getDescription(), "    ", true);
        out.flush();
    }

    public void examples(Examples examples) {
        out.println();
        printComments(examples.getComments(), "    ");
        printTags(examples.getTags(), "    ");
        out.println("    " + examples.getKeyword() + ": " + examples.getName());
        printDescription(examples.getDescription(), "    ", true);
        table(examples.getRows());
    }

    public void step(Step step) {
        printStep(step);
        if (step.getMultilineArg() instanceof List) {
            table((List<Row>) step.getMultilineArg());
        } else if (step.getMultilineArg() instanceof PyString) {
            pyString((PyString) step.getMultilineArg());
        }
    }

    private void printStep(Step step) {
        printComments(step.getComments(), "    ");
        String location = step.getResult() != null ? step.getResult().getStepdefLocation() : null;
        out.println("    " + step.getKeyword() + step.getName() + indentedStepLocation(location));
        if(step.getResult() != null && step.getResult().getErrorMessage() != null) {
            out.println(indent(step.getResult().getErrorMessage(), "      "));
        }
        out.flush();
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

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, long line) {
        throw new UnsupportedOperationException();
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

    private void padSpace(int indent, StringBuffer buffer) {
        for (int i = 0; i < indent; i++) {
            buffer.append(" ");
        }
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

    private String indentedScenarioLocation(String keyword, String name, long line) {
        if (maxStepLength == -1) return "";
        int l = keyword.length() + name.length();
        maxStepLength = Math.max(maxStepLength, l);
        int indent = maxStepLength - l;

        StringBuffer buffer = new StringBuffer();
        padSpace(indent, buffer);
        buffer.append(" ").append(comments("# " + uri + ":" + line, monochrome));
        return buffer.toString();
    }

    private String indentedStepLocation(String location) {
        if (location == null || "".equals(location)) return "";
        int indent = maxStepLength - stepLengths[stepIndex += 1];

        StringBuffer buffer = new StringBuffer();
        padSpace(indent, buffer);
        buffer.append(" ").append(comments("# " + location, monochrome));
        return buffer.toString();
    }

    private void printDescription(String description, String indentation, boolean newline) {
        if (!"".equals(description)) {
            out.println(indent(description, indentation));
            if (newline) out.println();
        }
    }

    private String indent(String s, String indentation) {
        return START.matcher(s).replaceAll(indentation);
    }

    private String escapeTripleQuotes(String s) {
        return TRIPLE_QUOTES.matcher(s).replaceAll("\\\\\"\\\\\"\\\\\"");
    }

}