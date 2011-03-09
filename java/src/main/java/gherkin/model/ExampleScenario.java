package gherkin.model;

import java.util.List;

public class ExampleScenario extends Scenario {
    public ExampleScenario(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void accept(Visitor v) {
        for (Step step : steps) {
            step.accept(v);            
        }
        v.visitExampleScenario(this);
    }
}
