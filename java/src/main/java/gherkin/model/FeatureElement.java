package gherkin.model;

import java.util.ArrayList;
import java.util.List;

public abstract class FeatureElement extends DescribedStatement {
    protected List<Step> steps = new ArrayList<Step>();
    public FeatureElement next;
    public Step step;

    public FeatureElement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    public Step newStep(List<Comment> comments, String keyword, String name, int line) {
        Step step = new Step(comments, keyword, name, line);
        return newStep(step);
    }

    public Step newStep(Step step) {
        steps.add(step);
        return step;
    }

    @Deprecated
    public List<Step> getSteps() {
        return steps;
    }

    public abstract void accept(Visitor v);

    public abstract void accept(NewVisitor v);
}
