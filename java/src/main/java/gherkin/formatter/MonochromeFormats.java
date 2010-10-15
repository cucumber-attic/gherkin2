package gherkin.formatter;

import java.io.PrintWriter;

public class MonochromeFormats implements Formats {
    private static final Format FORMAT = new Format() {
        public void writeText(PrintWriter out, String text) {
            out.write(text);
        }
    };

    public Format get(String key) {
        return FORMAT;
    }
}
