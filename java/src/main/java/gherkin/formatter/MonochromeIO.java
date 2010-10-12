package gherkin.formatter;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Pattern;

/**
 * This class filters away ANSI color codes
 */
public class MonochromeIO extends FilterWriter {
    private static final Pattern COLOR_PATTERN = Pattern.compile(new String(new char[(char)27]) + "\\[(?:[34][0-7]|[0-9]|90)?m");
    private static final String EMPTY_STRING = "";

    public MonochromeIO(Writer w) {
        super(w);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String monochromeString = COLOR_PATTERN.matcher(str).replaceAll(EMPTY_STRING);
        super.write(monochromeString, off, monochromeString.length());
    }

    /**
     * Convenience method for our ruby tests.
     * @return the String data in the underlying writer - if it is a StringWriter.
     */
    public String getString() {
        if(out instanceof StringWriter) {
            return out.toString();
        } else {
            throw new UnsupportedOperationException("Can only getString() from StringWriter");
        }
    }
}
