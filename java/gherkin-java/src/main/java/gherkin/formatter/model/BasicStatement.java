package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import gherkin.formatter.Mappable;

import java.util.List;

public abstract class BasicStatement extends Mappable implements CommentHolder {
    private final List<Comment> comments;
    private final String keyword;
    private final String name;
    private final Integer line;

    public BasicStatement(List<Comment> comments, String keyword, String name, Integer line) {
        this.comments = comments;
        this.keyword = keyword;
        this.name = name;
        this.line = line;
    }

    public Range getLineRange() {
        int first;
        if (getComments().size() > 0) {
            first = getComments().get(0).getLine();
        } else {
            first = getFirstNonCommentLine();
        }

        return new Range(first, getLine());
    }

    protected Integer getFirstNonCommentLine() {
        return line;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getName() {
        return name;
    }

    public Integer getLine() {
        return line;
    }

    public abstract void replay(Formatter formatter);
}
