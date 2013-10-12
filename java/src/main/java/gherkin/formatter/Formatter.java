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

    /**
     * Is called in case any syntax error was detected during the parsing of the feature files.
     *
     * @param state the current state of the parser machine
     * @param event detected event
     * @param legalEvents expected event
     * @param uri the URI of the feature file
     * @param line the line number of the event
     */
    void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line);

    /**
     * Called at the beginning of each feature.
     *
     * @param uri the feature's URI
     */
    void uri(String uri);

    /**
     * Called after the {@link Formatter#uri(String)}, but before the actual feature execution.
     *
     * @param feature the to be executed {@linkplain Feature}
     */
    void feature(Feature feature);

    /**
     * Called before the actual execution of the scenario outline step container.
     *
     * @param scenarioOutline the to be executed {@link ScenarioOutline}
     */
    void scenarioOutline(ScenarioOutline scenarioOutline);

    /**
     * Called before the actual execution of the scenario examples. This is called after
     * the {@link Formatter#scenarioOutline(gherkin.formatter.model.ScenarioOutline)},
     * but before any actual scenario example.
     *
     * @param examples the to be executed
     */
    void examples(Examples examples);

    /**
     * Is called at the beginning of the scenario life cycle, meaning before the first "before" hook.
     * @param scenario the {@link Scenario} of the current lifecycle
     */
    void startOfScenarioLifeCycle(Scenario scenario);

    /**
     * Called before the actual execution of the background step container.
     *
     * @param background the to be executed {@link Background}
     */
    void background(Background background);

    /**
     * Called before the actual execution of the scenario step container.
     *
     * @param scenario the to be executed {@link Scenario}
     */
    void scenario(Scenario scenario);

    /**
     * Is called for each step of a step container. <b>Attention:</b> All steps are iterated through
     * this method before any step is actually executed.
     *
     * @param step the {@link Step} to be executed
     */
    void step(Step step);

    /**
     * Is called at the end of the scenario life cycle, meaning after the last "after" hook.
     * * @param scenario the {@link Scenario} of the current lifecycle
     */
    void endOfScenarioLifeCycle(Scenario scenario);

    /**
     * Indicates that the last file has been processed. This should print out any closing output,
     * such as completing the JSON string, but it should *not* close any underlying streams/writers.
     */
    void done();

    /**
     * Closes all underlying streams.
     */
    void close();

    /**
     * Indicates the End-Of-File for a Gherkin document (.feature file)
     */
    void eof();
}
