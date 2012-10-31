package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import gherkin.formatter.visitors.Next;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScenarioOutline extends TagStatement implements StepContainer {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused") // it's in the JSON
    private final String type = "scenario_outline";
    private final Background background;
    private final List<Step> steps = new ArrayList<Step>();
    private final List<Examples> examplesList = new ArrayList<Examples>();

    @SuppressWarnings("unused") // Legacy API
    @Deprecated
    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
        this(comments, tags, keyword, name, description, line, id, null);
    }

    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id, Background background) {
        super(comments, tags, keyword, name, description, line, id);
        this.background = background;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenarioOutline(this);
    }

    @Override
    public List<Step> getSteps() {
        List<Step> result = new ArrayList<Step>();
        if (background != null) {
            result.addAll(background.getSteps());
        }
        result.addAll(steps);
        return Collections.unmodifiableList(result);
    }

    @Override
    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public List<Scenario> getScenarios() {
        List<Scenario> scenarios = new ArrayList<Scenario>();
        for (Examples examples : examplesList) {
            scenarios.addAll(examples.createScenarios(steps, background));
        }
        return scenarios;
    }

    @Override
    public void addExamples(Examples examples) {
        examplesList.add(examples);
    }

    @Override
    public void accept(Visitor visitor, Next next) throws Exception {
        if (background != null) {
            background.accept(visitor, next);
        }

        next.pushAll(steps);
        next.pushAll(examplesList);
        visitor.visitScenarioOutline(this, next);
    }

}
