package gherkin.model;

import java.util.List;

public abstract class DescribedStatement extends BasicStatement {
    private final List<Tag> tags;
    private final String description;

    public DescribedStatement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, keyword, name, line);
        this.tags = tags;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public final List<Tag> getTags() {
        return tags;
    }

    @Override
    protected int getFirstNonCommentLine() {
        if (getTags().isEmpty()) {
            return getLine();
        } else {
            return getTags().get(0).getLine();
        }
    }
}
