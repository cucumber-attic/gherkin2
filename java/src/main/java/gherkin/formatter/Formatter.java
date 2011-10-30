package gherkin.formatter;

import gherkin.formatter.model.*;

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

    void eof();

    void syntaxError(String state, String event, List<String> legalEvents, String uri, int line);

    void close();
}
