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

    void scenario(Scenario scenario);

    void scenarioOutline(ScenarioOutline scenarioOutline);

    void examples(Examples examples);

    void step(Step step);

    /**
     * Indicates the End-Of-File for a Gherkin document (.feature file)
     */
    void eof();

    void syntaxError(String state, String event, List<String> legalEvents, String uri, int line);

    /**
     * Indicates that the last file has been processed.
     */
    void done();
}
