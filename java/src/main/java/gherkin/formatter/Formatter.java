package gherkin.formatter;

import gherkin.Listener;

import java.util.List;

/**
 * This is the interface you should implement if you want your own custom
 * formatter.
 */
public interface Formatter {
    void feature(List<String> comments, List<String> tags, String keyword, String name, String uri);
    void background(List<String> comments, String keyword, String name, int line);
    void scenario(List<String> comments, List<String> tags, String keyword, String name, int line);
    void scenarioOutline(List<String> comments, List<String> tags, String keyword, String name, int line);
    void examples(List<String> comments, List<String> tags, String keyword, String name, int line, List<List<String>> exampleRows);

    /**
     * Invoked after a step has been executed.
     *
     * @param comments comments in front of the step.
     * @param keyword the value of step keyword ("Given ", "When ", "Then " etc).
     * @param name the text of the step, for example "I have 5 cukes".
     * @param line the line of the step.
     * @param stepTable a table argument
     * @param status whether or not we failed. TODO: USE ENUM
     * @param thrown the exception that was thrown, or null if none was thrown.
     * @param arguments The arguments the step was invoked with.
     * @param stepdefLocation the location of the step definition.
     */
    void step(List<String> comments, String keyword, String name, int line, List<List<String>> stepTable, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation);
    void step(List<String> comments, String keyword, String name, int line, String stepString,            String status, Throwable thrown, List<Argument> arguments, String stepdefLocation);
    void eof();

    void table(List<List<String>> rows);
}
