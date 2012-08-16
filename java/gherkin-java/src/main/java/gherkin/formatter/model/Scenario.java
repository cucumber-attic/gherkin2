package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class Scenario extends TagStatement {
    private static final long serialVersionUID = 1L;

    private final String type = "scenario";

    public Scenario(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
        super(comments, tags, keyword, name, description, line, id);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenario(this);
    }
}
