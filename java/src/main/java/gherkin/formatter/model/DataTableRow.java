package gherkin.formatter.model;

import java.util.List;

public class DataTableRow extends Row {
    private transient final DiffType diffType;


    public DataTableRow(List<Comment> comments, List<String> cells, Integer line) {
        this(comments, cells, line, DiffType.NONE);
    }

    public DataTableRow(List<Comment> comments, List<String> cells, Integer line, DiffType diffType) {
        super(comments, cells, line);
        this.diffType = diffType;
    }

    public DiffType getDiffType() {
        return diffType;
    }
}
