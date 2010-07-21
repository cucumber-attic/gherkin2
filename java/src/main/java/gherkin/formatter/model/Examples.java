package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class Examples extends TagStatement {
    private List<Row> rows;

    public Examples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line, List<Row> rows) {
        super(comments, tags, keyword, name, description, line);
        this.rows = rows;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.examples(this);
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
