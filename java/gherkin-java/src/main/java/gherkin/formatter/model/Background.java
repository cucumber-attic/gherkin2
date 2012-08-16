package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class Background extends DescribedStatement {
    private final String type = "background";

    public Background(List<Comment> comments, String keyword, String name, String description, Integer line) {
        super(comments, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.background(this);
    }
}
