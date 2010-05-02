package gherkin.formatter;

import gherkin.Listener;

import java.util.List;

/**
 * This interface extends the Listener interface with extra methods for formatting
 * Gherkin features during execution.
 */
public interface Formatter extends Listener {
    void scenario(String keyword, String name, int line, String location);

    /**
     * Invoked after a step has been executed.
     *
     * @param keyword the value of step keyword ("Given ", "When ", "Then " etc).
     * @param name the text of the step, for example "I have 5 cukes".
     * @param line the line of the step.
     * @param status whether or not we failed. TODO: USE ENUM
     * @param thrown the exception that was thrown, or null if none was thrown.
     * @param arguments The arguments the step was invoked with.
     * @param sourceLocation the location of the step definition.
     */
    void step(String keyword, String name, int line, String status, Throwable thrown, List<Argument> arguments, String sourceLocation);

    void flushTable();
}
