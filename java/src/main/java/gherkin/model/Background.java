package gherkin.model;

import gherkin.formatter.Formatter;

import java.util.Collections;
import java.util.List;

public class Background extends FeatureElement {
    private final String type = "background";

    public Background(List<Comment> comments, String keyword, String name, String description, int line) {
        super(comments, Collections.<Tag>emptyList(), keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.background(this);
    }

    @Override
    public void accept(Visitor v) {
        for (Step step : steps) {
            step.accept(v);
        }
        v.visitBackground(this);
    }
}
