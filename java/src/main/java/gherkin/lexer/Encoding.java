package gherkin.lexer;

import gherkin.util.FixJava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities for reading the encoding of a file.
 */
public class Encoding {
    private static final Pattern COMMENT_OR_EMPTY_LINE_PATTERN = Pattern.compile("^\\s*#|^\\s*$");
    private static final Pattern ENCODING_PATTERN = Pattern.compile("^\\s*#\\s*encoding\\s*:\\s*([0-9a-zA-Z\\-]+)", Pattern.CASE_INSENSITIVE);
    public static final String DEFAULT_ENCODING = "UTF-8";

    public String readFile(String path) throws FileNotFoundException, UnsupportedEncodingException {
        String source = FixJava.readReader(new InputStreamReader(new FileInputStream(path), DEFAULT_ENCODING));
        String enc = encoding(source);
        if(!enc.equals(DEFAULT_ENCODING)) {
            source = FixJava.readReader(new InputStreamReader(new FileInputStream(path), enc));
        }
        return source;
    }

    public String encoding(String source) {
        String encoding = DEFAULT_ENCODING;
        for (String line : source.split("\\n")) {
            if (!COMMENT_OR_EMPTY_LINE_PATTERN.matcher(line).find()) {
                break;
            }
            Matcher matcher = ENCODING_PATTERN.matcher(line);
            if (matcher.find()) {
                encoding = matcher.group(1);
                break;
            }
        }
        return encoding.toUpperCase();
    }
}
