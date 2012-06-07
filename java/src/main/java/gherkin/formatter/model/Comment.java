package gherkin.formatter.model;

import gherkin.formatter.Mappable;

public class Comment extends Mappable {
    private static final long serialVersionUID = 1L;

    private final String value;
    private final Integer line;

    public Comment(String value, Integer line) {
        this.value = value;
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public Integer getLine() {
        return line;
    }
}
