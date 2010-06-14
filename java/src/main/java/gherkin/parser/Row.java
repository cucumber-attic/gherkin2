package gherkin.parser;

import java.util.List;

public class Row {
    private final List<String> cells;
    private final List<String> comments;
    private final int line;

    public List<String> getCells() {
        return cells;
    }

    public List<String> getComments() {
        return comments;
    }

    public int getLine() {
        return line;
    }

    public Row(List<String> cells, List<String> comments, int line) {
        this.cells = cells;
        this.comments = comments;
        this.line = line;
    }
}
