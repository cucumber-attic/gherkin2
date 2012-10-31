package gherkin.formatter.model;

import gherkin.formatter.visitors.Next;

public interface Visitor {
    void visitFeature(Feature feature, Next next) throws Exception;

    void visitBackground(Background background, Next next) throws Exception;

    void visitScenario(Scenario scenario, Next next) throws Exception;

    void visitScenarioOutline(ScenarioOutline scenarioOutline, Next next) throws Exception;

    void visitStep(Step step, Next next) throws Exception;

    void visitDataTableRow(DataTableRow row, Next next) throws Exception;

    void visitExamples(Examples examples) throws Exception;

    void visitDocString(DocString docString, Next next) throws Exception;
}
