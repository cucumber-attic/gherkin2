package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Feature extends TagStatement {
    private static final long serialVersionUID = 1L;
    private final List<StepContainer> stepContainers = new ArrayList<StepContainer>();

    public Feature(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
        super(comments, tags, keyword, name, description, line, id);
    }

    public List<Scenario> getStepContainers() {
        List<Scenario> result = new ArrayList<Scenario>();
        for (StepContainer stepContainer : stepContainers) {
            result.addAll(stepContainer.getScenarios());
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.feature(this);
    }

    public void addStepContainer(StepContainer stepContainer) {
        stepContainers.add(stepContainer);
    }
}
