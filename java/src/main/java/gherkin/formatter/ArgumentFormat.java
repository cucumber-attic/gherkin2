package gherkin.formatter;

import java.io.UnsupportedEncodingException;

public class ArgumentFormat {
    private final String prefix;
    private final String suffix;

    public ArgumentFormat(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public byte[] formatArgument(byte[] argument) throws UnsupportedEncodingException {
        return (prefix + new String(argument, "UTF-8") + suffix).getBytes("UTF-8");
    }
}
