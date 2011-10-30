package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Examples extends TagStatement {
    private List<Row> rows;

    public static class Builder implements gherkin.formatter.model.Builder {
        private final List<Comment> comments;
        private final List<Tag> tags;
        private final String keyword;
        private final String name;
        private final String description;
        private final int line;
        private List<Row> rows;

        public Builder(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
            this.comments = comments;
            this.tags = tags;
            this.keyword = keyword;
            this.name = name;
            this.description = description;
            this.line = line;
        }

        public void row(List<Comment> comments, List<String> cells, int line) {
            if (rows == null) {
                rows = new ArrayList<Row>();
            }
            rows.add(new ExamplesTableRow(comments, cells, line));
        }

        public void replay(Formatter formatter) {
            new Examples(comments, tags, keyword, name, description, line, rows).replay(formatter);
        }

        public void docString(DocString docString) {
            throw new IllegalStateException("Can't have DocString in Examples");
        }
    }

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
