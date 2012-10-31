package gherkin.formatter.model;

import java.util.List;

public interface StepContainer extends Visitable {
    List<Step> getSteps();

    void addStep(Step step);

    List<Scenario> getScenarios();

    void addExamples(Examples examples);
}
