package gherkin.formatter;

import gherkin.listener.Row;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

import static gherkin.formatter.Colors.comments;
import static gherkin.util.FixJava.join;

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

    public PrettyFormatter(Writer out, boolean monochrome) {
        this.out = new PrintWriter(out);
        this.monochrome = monochrome;
    }

    public PrettyFormatter(OutputStream out, boolean monochrome) throws UnsupportedEncodingException {
        this(new OutputStreamWriter(out, "UTF-8"), monochrome);
    }

    public void feature(List<String> comments, List<String> tags, String keyword, String name, String description, String uri) {
        this.uri = uri;
        printComments(comments, "");
        printTags(tags, "");
        out.println(keyword + ": " + name);
        printDescription(description, "  ", false);
        out.flush();
    }

    public void background(List<String> comments, String keyword, String name, String description, int line) {
        out.println();
        printComments(comments, "  ");
        out.println("  " + keyword + ": " + name);
        printDescription(description, "    ", true);
    }

    public void scenario(List<String> comments, List<String> tags, String keyword, String name, String description, int line) {
        out.println();
        printComments(comments, "  ");
        printTags(tags, "  ");
        out.println("  " + keyword + ": " + name + indentedScenarioLocation(keyword, name, line));
        printDescription(description, "    ", true);
        out.flush();
    }

    public void scenarioOutline(List<String> comments, List<String> tags, String keyword, String name, String description, int line) {
        scenario(comments, tags, keyword, name, description, line);
    }

    public void examples(List<String> comments, List<String> tags, String keyword, String name, String description, int line, List<Row> exampleRows) {
        out.println();
        printComments(comments, "    ");
        printTags(tags, "    ");
        out.println("    " + keyword + ": " + name);
        printDescription(description, "    ", true);
        table(exampleRows);
    }

    public void step(List<String> comments, String keyword, String name, int line, List<Row> stepTable, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {
        step(comments, keyword, name, stepdefLocation);
        if (stepTable != null) {
            table(stepTable);
        }
    }

    public void step(List<String> comments, String keyword, String name, int line, String stepString, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {
        step(comments, keyword, name, stepdefLocation);
        if (stepString != null) {
            pyString(stepString);
        }
    }

    private void step(List<String> comments, String keyword, String name, String stepdefLocation) {
        printComments(comments, "    ");
        out.println("    " + keyword + name + indentedStepLocation(stepdefLocation));
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
            for(String comment : rows.get(i).getComments()) {
                out.write("      ");
                out.println(comment);
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

    private String escapeCell(String cell) {
        return cell.replaceAll("\\|", "\\\\|").replaceAll("\\\\(?!\\|)", "\\\\\\\\");
    }

    public void pyString(String string) {
        out.println("      \"\"\"");
        out.print(escapeTripleQuotes(indent(string, "      ")));
        out.println("\n      \"\"\"");
    }

    public void eof() {
        out.flush();
    }

    public void steps(List<List<String>> steps) {
        stepLengths = new int[steps.size()];
        int i = 0;
        for (List<String> step : steps) {
            int stepLength = step.get(0).length() + step.get(1).length();
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

    private void printComments(List<String> comments, String indent) {
        for (String comment : comments) {
            out.print(indent);
            out.println(comment);
        }
        out.flush();
    }

    private void printTags(List<String> tags, String indent) {
        if(tags.isEmpty()) return;
        out.print(indent);
        out.println(join(tags, " "));
        out.flush();
    }

    private String indentedScenarioLocation(String keyword, String name, int line) {
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
        if(!"".equals(description)) {
            out.println(indent(description, indentation));
            if(newline) out.println();
        }
    }

    private String indent(String s, String indentation) {
        return START.matcher(s).replaceAll(indentation);
    }

    private String escapeTripleQuotes(String s) {
        return TRIPLE_QUOTES.matcher(s).replaceAll("\\\\\"\\\\\"\\\\\"");
    }

}