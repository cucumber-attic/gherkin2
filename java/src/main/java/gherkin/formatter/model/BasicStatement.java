package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.lang.reflect.Field;
import java.util.*;

public abstract class BasicStatement extends Mappable implements CommentHolder {
    private final List<Comment> comments;
    private final String keyword;
    private final String name;
    private final String description;
    private final int line;

    public BasicStatement(List<Comment> comments, String keyword, String name, String description, int line) {
        this.comments = comments;
        this.keyword = keyword;
        this.name = name;
        this.description = description;
        this.line = line;
    }

    public Range getLineRange() {
        int first;
        if(getComments().size() > 0) {
            first = getComments().get(0).getLine();
        } else {
            first = getFirstNonCommentLine();
        }

        return new Range(first, getLine());
    }

    protected int getFirstNonCommentLine() {
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

    public String getDescription() {
        return description;
    }

    public int getLine() {
        return line;
    }

    public abstract void replay(Formatter formatter);
}
