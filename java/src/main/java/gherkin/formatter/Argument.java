package gherkin.formatter;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Argument {
    private final int byteOffset;
    private final byte[] val;

    public Argument(int byteOffset, String val) {
        try {
            this.byteOffset = byteOffset;
            this.val = val.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(String string, ArgumentFormat format, List<Argument> arguments) {
        return format(string, format, (Argument[]) arguments.toArray(new Argument[arguments.size()]));
    }

    public static String format(String string, ArgumentFormat format, Argument... arguments) {
        try {
            byte[] result = string.getBytes("UTF-8");
            int offset = 0, pastOffset = 0;
            for (Argument argument : arguments) {
                if (argument.byteOffset != -1 && argument.byteOffset >= pastOffset) {
                    byte[] replacement = format.formatArgument(argument.val);
                    int delta = replacement.length - argument.val.length;
                    byte[] newResult = new byte[result.length + delta];

                    System.arraycopy(result, 0, newResult, 0, argument.byteOffset + offset);
                    System.arraycopy(replacement, 0, newResult, argument.byteOffset + offset, replacement.length);
                    int n = argument.byteOffset + argument.val.length + offset;
                    System.arraycopy(result, n, newResult, argument.byteOffset + replacement.length, result.length - n);

                    offset += delta;
                    result = newResult;
                }
            }
            return new String(result, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getVal() {
        try {
            return new String(val, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @deprecated
     */
    public int getByteOffset() {
        return byteOffset;
    }
}
