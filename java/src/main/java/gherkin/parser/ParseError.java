package gherkin.parser;

import gherkin.util.FixJava;

import java.util.List;

public class ParseError extends RuntimeException {
    private final String state;
    private final List<String> expectedEvents;

    public ParseError(String state, String event, List<String> expectedEvents, int line) {
        super("Parse error on line " + line + ". Found " + event + " when expecting one of: " + FixJava.join(expectedEvents, ", ") + ". (Current state: " + state + ").");
        this.state = state;
        this.expectedEvents = expectedEvents;
    }

    public String state() {
        return state;
    }

    public List<String> expectedEvents() {
        return expectedEvents;
    }
}
