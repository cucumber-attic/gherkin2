package gherkin.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class Scenario extends FeatureElement {
    private final String type = "scenario";

    public Scenario(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenario(this);
    }

    public void accept(Visitor v) {
        for (Step step : steps) {
            step.accept(v);            
        }
        v.visitScenario(this);
    }

    @Override
    public void accept(final NewVisitor v) {
        v.visitScenario(this, new It() {
            public void next() {
                step.accept(v);
            }
        });
    }
}
