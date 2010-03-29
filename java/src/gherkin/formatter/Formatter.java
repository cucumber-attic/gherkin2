package gherkin.formatter;

import gherkin.Listener;

import java.util.List;

/**
 * This interface extends the Listener interface with extra methods for formatting
 * Gherkin features during execution.
 */
public interface Formatter extends Listener {
    void scenario(String keyword, String name, int line, String location);
    void step(String keyword, String name, int line, String status, List<Argument> arguments, String location);
    void flushTable();
}
