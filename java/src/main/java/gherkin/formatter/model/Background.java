package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import gherkin.formatter.visitors.Next;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Background extends DescribedStatement implements StepContainer {
    @SuppressWarnings("unused")
    private final String type = "background";
    private List<Step> steps = new ArrayList<Step>();

    public Background(List<Comment> comments, String keyword, String name, String description, Integer line) {
        super(comments, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.background(this);
    }

    @Override
    public List<Step> getSteps() {
        return Collections.unmodifiableList(steps);
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public List<Scenario> getScenarios() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addExamples(Examples examples) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accept(Visitor visitor, Next next) throws Exception {
        next.pushAll(steps);
        visitor.visitBackground(this, next);
    }
}
