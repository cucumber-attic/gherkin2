package gherkin.model;

public interface NewVisitor {
    void visitFeature(Feature feature, It it);
    void visitBackground(Background background, It it);
    void visitScenario(Scenario scenario, It it);
    void visitScenarioOutline(ScenarioOutline scenarioOutline, It it);
    void visitStep(Step step, It it);
    void visitExamples(Examples examples, It it);
    void visitExampleScenario(ExampleScenario exampleScenario, It it);
}
