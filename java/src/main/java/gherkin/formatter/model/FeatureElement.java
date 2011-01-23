package gherkin.formatter.model;

import java.util.ArrayList;
import java.util.List;

public abstract class FeatureElement extends DescribedStatement {
    private List<Step> steps = new ArrayList<Step>();

    public FeatureElement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    public Step newStep(List<Comment> comments, String keyword, String name, int line) {
        Step step = new Step(comments, keyword, name, line);
        steps.add(step);
        return step;
    }
}
