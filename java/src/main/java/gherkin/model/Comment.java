package gherkin.model;

import gherkin.formatter.Mappable;

public class Comment extends Mappable {
    private final String value;
    private final int line;

    public Comment(String value, int line) {
        this.value = value;
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }
}
