package gherkin.formatter.model;

import java.util.List;

public class ExamplesTableRow extends Row {
    public ExamplesTableRow(List<Comment> comments, List<String> cells, int line) {
        super(comments, cells, line);
    }

    @Override
    public DiffType getDiffType() {
        return DiffType.NONE;
    }
}
