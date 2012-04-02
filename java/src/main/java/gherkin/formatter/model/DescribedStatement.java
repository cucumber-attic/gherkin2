package gherkin.formatter.model;

import java.util.List;

public abstract class DescribedStatement extends BasicStatement {
    private final String description;

    public DescribedStatement(List<Comment> comments, String keyword, String name, String description, Integer line) {
        super(comments, keyword, name, line);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
