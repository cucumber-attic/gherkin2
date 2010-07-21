package gherkin.formatter.model;

public class PyString extends Mappable {
    private final String value;
    private final int line;

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public PyString(String value, int line) {
        this.value = value;
        this.line = line;
    }

    public Range getLineRange() {
        int lineCount = value.split("\r?\n").length;
        return new Range(getLine(), getLine() + lineCount + 1);
    }
}
