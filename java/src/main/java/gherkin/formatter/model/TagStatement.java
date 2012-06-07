package gherkin.formatter.model;

import java.util.List;

public abstract class TagStatement extends DescribedStatement {
    private static final long serialVersionUID = 1L;

    private final List<Tag> tags;
    private final String id;

    public TagStatement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
        super(comments, keyword, name, description, line);
        this.tags = tags;
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    protected Integer getFirstNonCommentLine() {
        if (getTags().isEmpty()) {
            return getLine();
        } else {
            return getTags().get(0).getLine();
        }
    }
}
