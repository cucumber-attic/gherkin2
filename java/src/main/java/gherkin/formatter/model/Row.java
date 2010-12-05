package gherkin.formatter.model;

import gherkin.formatter.Mappable;

import java.util.ArrayList;
import java.util.List;

public class Row extends Mappable implements CommentHolder {
    private final List<Comment> comments;
    private final List<String> cells;
    private final int line;

    public Row(List<Comment> comments, List<String> cells, int line) {
        if (comments == null) {
            throw new NullPointerException("comments");
        }
        if (cells == null) {
            throw new NullPointerException("cells");
        }
        this.comments = comments;
        this.cells = cells;
        this.line = line;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<String> getCells() {
        return cells;
    }

    public int getLine() {
        return line;
    }

    public List<CellResult> createResults(String status) {
        List<CellResult> results = new ArrayList<CellResult>();
        for (String cell : cells) {
            results.add(new CellResult(status));
        }
        return results;
    }
}
