package gherkin.formatter;

import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class JansiFormats implements Formats {
    private static final String GREY = new StringBuilder().append((char) 27).append("[90m").toString();

    private static final Map<String, Format> formats = new HashMap<String, Format>() {{
        put("undefined", new ColorFormat(YELLOW, null));
        put("pending", new ColorFormat(YELLOW, null));
        put("pending_param", new ColorFormat(YELLOW, INTENSITY_BOLD));
        put("executing", new ColorFormat(MAGENTA, null));
        put("executing_param", new ColorFormat(MAGENTA, INTENSITY_BOLD));
        put("failed", new ColorFormat(RED, null));
        put("failed_param", new ColorFormat(RED, INTENSITY_BOLD));
        put("passed", new ColorFormat(GREEN, null));
        put("passed_param", new ColorFormat(GREEN, INTENSITY_BOLD));
        put("outline", new ColorFormat(CYAN, null));
        put("outline_param", new ColorFormat(CYAN, INTENSITY_BOLD));
        put("skipped", new ColorFormat(CYAN, null));
        put("skipped_param", new ColorFormat(CYAN, INTENSITY_BOLD));
        put("comment", new ColorFormat(GREY, null));
        put("tag", new ColorFormat(CYAN, null));
    }};

    public static class ColorFormat implements Format {
        private final Object color;
        private final Ansi.Attribute attribute;

        public ColorFormat(Object color, Ansi.Attribute attribute) {
            this.color = color;
            this.attribute = attribute;
        }

        public String text(String text) {
            Ansi a = ansi();
            if (color instanceof Ansi.Color) {
                a.fg((Ansi.Color) color);
            } else {
                a.a(color);
            }
            if (attribute != null) {
                a.a(attribute);
            }
            return a.a(text).reset().toString();
        }
    }

    public Format get(String key) {
        return formats.get(key);
    }

    public String up(int n) {
        return ansi().cursorUp(n).toString();
    }
}
