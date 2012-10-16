package gherkin.formatter.model;

import java.util.Collection;
import java.util.List;

public interface StepContainer {
    List<Step> getSteps();

    void addStep(Step step);

    void addScenario(ExamplesTableRow header, ExamplesTableRow row, List<Tag> tags);

    Collection<Scenario> getScenarios();
}
