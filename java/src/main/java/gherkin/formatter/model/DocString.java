package gherkin.formatter.model;

import gherkin.formatter.Mappable;

public class DocString extends Mappable {
    private final String content_type;
    private final String value;
    private final Integer line;

    public String getContentType() {
        return content_type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public DocString(String contentType, String value, Integer line) {
        this.content_type = contentType;
        this.value = value;
        this.line = line;
    }

    public Range getLineRange() {
        int lineCount = value.split("\r?\n").length;
        return new Range(getLine(), getLine() + lineCount + 1);
    }
}
