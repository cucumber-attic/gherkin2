package gherkin.formatter;

import java.util.HashMap;
import java.util.Map;

public class ConfluenceMarkup implements Formats {

    public static enum Formats {
        BOLD, COLOR_DARK_GREY, COLOR_RED, HEADER1, HEADER2, INFO, ITALICS, PANEL,
        TABLE_CELL, TABLE_HEAD, TABLE_HEAD_CELL, TABLE_ROW
    }

    private static final Map<Formats, Format> formats = new HashMap<Formats, Format>() {{
        put(Formats.HEADER1, new PrefixingFormat("h1. "));
        put(Formats.HEADER2, new PrefixingFormat("h2. "));
        put(Formats.ITALICS, new EnclosingFormat("_"));
        put(Formats.BOLD, new EnclosingFormat("*"));
        put(Formats.COLOR_RED, new Color("red"));
        put(Formats.COLOR_DARK_GREY, new Color("#666666"));
        put(Formats.TABLE_ROW, new PrefixingFormat("|"));
        put(Formats.TABLE_HEAD, new PrefixingFormat("||"));
        put(Formats.TABLE_HEAD_CELL, new SuffixingFormat("||"));
        put(Formats.TABLE_CELL, new SuffixingFormat(" |"));
        put(Formats.PANEL, new CurlyBracesEnclosedFormat("panel", "panel"));
        put(Formats.INFO, new CurlyBracesEnclosedFormat("info", "info"));
    }};

    public static class EnclosingFormat implements Format {
        private final String enclosure;

        public EnclosingFormat(String enclosure) {
            this.enclosure = enclosure;
        }

        public String text(String text) {
            return enclosure + text + enclosure;
        }
    }

    public static class PrefixingFormat implements Format {
        private final String prefix;

        public PrefixingFormat(String prefix) {
            this.prefix = prefix;
        }

        public String text(String text) {
            return prefix + text;
        }
    }

    public static class SuffixingFormat implements Format {
        private final String suffix;

        public SuffixingFormat(String suffix) {
            this.suffix = suffix;
        }

        public String text(String text) {
            return text + suffix;
        }
    }

    public static class CurlyBracesEnclosedFormat implements Format {
        private final String openingTagContent;
        private String closingTagContent;

        public CurlyBracesEnclosedFormat(String openingTagContent, String closingTagContent) {
            this.openingTagContent = openingTagContent;
            this.closingTagContent = closingTagContent;
        }

        public String text(String text) {
            return "{" + openingTagContent + "}" + text + "{" + closingTagContent + "}";
        }
    }

    public static class Color extends CurlyBracesEnclosedFormat {

        public Color(String color) {
            super("color:" + color, "color");
        }
    }

    public Format get(String key) {
        Format format = formats.get(Formats.valueOf(key));
        if (format == null) throw new NullPointerException("No format for key " + key);
        return format;
    }

    public Format get(Formats key) {
        return formats.get(key);
    }

    public String up(int n) {
        return "";
    }
}


