package gherkin.formatter;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * A nice appendable that doesn't throw checked exceptions
 */
public class NiceAppendable {
    private static final CharSequence NL = "\n";
    private final Appendable out;

    public NiceAppendable(Appendable out) {
        this.out = out;
    }

    public NiceAppendable append(CharSequence csq) {
        try {
            out.append(csq);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NiceAppendable append(CharSequence csq, int start, int end) {
        try {
            out.append(csq, start, end);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NiceAppendable append(char c) {
        try {
            out.append(c);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NiceAppendable println() {
        return append(NL);
    }

    public NiceAppendable println(CharSequence csq) {
        return append(csq).println();
    }

    public void close() {
        try {
            if (out instanceof Flushable) {
                ((Flushable) out).flush();
            }
            if (out instanceof Closeable) {
                ((Closeable) out).close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
