package gherkin.parser;

import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.Step;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AstListenerTest {
    private AstListener l = new AstListener();

    @Test
    public void builds_simple_scenario() {
        l.feature("Feature", "Test", "Desc", 1);
        l.scenario("Scenario", "Test", "Desc", 2);
        l.step("Given ", "Test", 3);
        l.eof();

        Feature f = l.getFeature();
        assertEquals("Feature", f.getKeyword());
        Scenario scenario = f.getStepContainers().get(0);
        assertEquals("Scenario", scenario.getKeyword());
        assertEquals("Given ", scenario.getSteps().get(0).getKeyword());
    }

    @Test
    public void builds_simple_scenario_with_table() {
        l.feature("Feature", "Test", "Desc", 1);
        l.scenario("Scenario", "Test", "Desc", 2);
        l.step("Given ", "Test", 3);
        l.row(asList("col1", "col2"), 4);
        l.eof();

        Step step = l.getFeature().getStepContainers().get(0).getSteps().get(0);
        assertEquals("col2", step.getRows().get(0).getCells().get(1));
    }

    @Test
    public void builds_simple_scenario_with_doc_string() {
        l.feature("Feature", "Test", "Desc", 1);
        l.scenario("Scenario", "Test", "Desc", 2);
        l.step("Given ", "Test", 3);
        l.docString(null, "docstring", 4);
        l.eof();

        Step step = l.getFeature().getStepContainers().get(0).getSteps().get(0);
        assertEquals("docstring", step.getDocString().getValue());
    }

    @Test
    public void builds_scenario_with_background() {
        l.feature("Feature", "Test", "Desc", 1);
        l.background("Background", "Test", "Desc", 2);
        l.step("Given ", "B step", 3);
        l.scenario("Scenario", "Test", "Desc", 4);
        l.step("When ", "S step", 5);
        l.eof();

        Feature f = l.getFeature();
        assertEquals("Feature", f.getKeyword());
        Scenario scenario = f.getStepContainers().get(0);
        assertEquals("Scenario", scenario.getKeyword());
        assertEquals("Given ", scenario.getSteps().get(0).getKeyword());
    }

    @Test
    public void builds_scenario_outline() throws IOException {
        l.feature("Feature", "Test", "Desc", 1);
        l.scenarioOutline("Scenario Outline", "Test", "Desc", 2);
        l.step("Given ", "I have <n> cukes", 3);
        l.examples("Examples", "e", "desc", 4);
        l.row(asList("n"), 5);
        l.row(asList("2"), 6);
        l.row(asList("7"), 7);
        l.eof();

        Feature f = l.getFeature();

        assertEquals(2, f.getStepContainers().size());

        assertEquals("I have 2 cukes", l.getFeature().getStepContainers().get(0).getSteps().get(0).getName());
        assertEquals("I have 7 cukes", l.getFeature().getStepContainers().get(1).getSteps().get(0).getName());
    }

    @Test
    public void builds_scenario_outline_with_background() {
        l.feature("Feature", "Test", "Desc", 1);
        l.background("Background", "Test", "Desc", 2);
        l.step("Given ", "B <n> step left intact", 3);
        l.scenarioOutline("Scenario Outline", "Test", "Desc", 4);
        l.step("Given ", "I have <n> cukes", 5);
        l.examples("Examples", "e", "desc", 6);
        l.row(asList("n"), 7);
        l.row(asList("2"), 8);
        l.row(asList("7"), 9);
        l.eof();

        Feature f = l.getFeature();
        assertEquals(2, f.getStepContainers().size());

        Scenario s0 = l.getFeature().getStepContainers().get(0);
        List<Step> s0steps = s0.getSteps();
        assertEquals("B <n> step left intact", s0steps.get(0).getName());
        assertEquals("I have 2 cukes", s0steps.get(1).getName());

        Scenario s1 = l.getFeature().getStepContainers().get(1);
        assertEquals("B <n> step left intact", s1.getSteps().get(0).getName());
        assertEquals("I have 7 cukes", s1.getSteps().get(1).getName());
    }
}
