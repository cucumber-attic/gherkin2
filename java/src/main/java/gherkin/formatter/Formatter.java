package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

import java.util.List;

/**
 * This is the interface you should implement if you want your own custom
 * formatter.
 */
public interface Formatter {
    void uri(String uri);

    void feature(Feature feature);

    void background(Background background);

    void scenario(Scenario statement);

    void scenarioOutline(ScenarioOutline statement);

    void examples(Examples examples);

    void step(Step step);

    void eof();

    void syntaxError(String state, String event, List<String> legalEvents, String uri, int line);

    void steps(List<Step> steps);
}
