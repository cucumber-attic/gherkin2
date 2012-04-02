package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Examples extends TagStatement {
    private List<ExamplesTableRow> rows;

    public static class Builder implements gherkin.formatter.model.Builder {
        private final List<Comment> comments;
        private final List<Tag> tags;
        private final String keyword;
        private final String name;
        private final String description;
        private final Integer line;
        private final String id;
        private List<ExamplesTableRow> rows;

        public Builder(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
            this.comments = comments;
            this.tags = tags;
            this.keyword = keyword;
            this.name = name;
            this.description = description;
            this.line = line;
            this.id = id;
        }

        public void row(List<Comment> comments, List<String> cells, Integer line, String id) {
            if (rows == null) {
                rows = new ArrayList<ExamplesTableRow>();
            }
            rows.add(new ExamplesTableRow(comments, cells, line, id));
        }

        public void replay(Formatter formatter) {
            new Examples(comments, tags, keyword, name, description, line, id, rows).replay(formatter);
        }

        public void docString(DocString docString) {
            throw new IllegalStateException("Can't have DocString in Examples");
        }
    }

    public Examples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id, List<ExamplesTableRow> rows) {
        super(comments, tags, keyword, name, description, line, id);
        this.rows = rows;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.examples(this);
    }

    public List<ExamplesTableRow> getRows() {
        return rows;
    }

    public void setRows(List<ExamplesTableRow> rows) {
        this.rows = rows;
    }
}
