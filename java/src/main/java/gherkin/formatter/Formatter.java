package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

import java.io.Closeable;
import java.util.List;

/**
 * This is the interface you should implement if you want your own custom
 * formatter.
 */
public interface Formatter extends Closeable {
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
     * Indicates that the last file has been processed. This should print out any closing output,
     * such as completing the JSON string, but it should *not* close any underlying streams/writers.
     */
    void done();

    /**
     * Closes all underlying streams.
     */
    void close();
}
