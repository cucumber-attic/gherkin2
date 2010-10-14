package gherkin.formatter;

import java.io.PrintWriter;

public interface Format {
    void writeText(PrintWriter out, String text);
}
