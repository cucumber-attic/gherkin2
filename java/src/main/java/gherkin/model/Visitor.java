package gherkin.model;

public interface Visitor {
    void visitFeature(Feature feature);
    void visitBackground(Background background);
    void visitScenario(Scenario scenario);
    void visitScenarioOutline(ScenarioOutline scenarioOutline);
    void visitStep(Step step);
    void visitExamples(Examples examples);
    void visitExampleScenario(ExampleScenario exampleScenario);
}
