package gherkin.formatter.model;

import java.util.List;

public abstract class TagStatement extends BasicStatement {
    private final List<Tag> tags;

    public TagStatement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, keyword, name, description, line);
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    protected int getFirstNonCommentLine() {
        if(getTags().isEmpty()) {
            return getLine();
        } else {
            return getTags().get(0).getLine();
        }
    }
}
