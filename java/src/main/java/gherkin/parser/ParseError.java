package gherkin.parser;

import gherkin.util.FixJava;

import java.util.List;

public class ParseError extends RuntimeException {
    private final String state;
    private final List<String> legalEvents;

    public ParseError(String state, String event, List<String> legalEvents, String uri, int line) {
        super("Parse error at " + uri + ":" + line + ". Found " + event + " when expecting one of: " + FixJava.join(legalEvents, ", ") + ". (Current getState: " + state + ").");
        this.state = state;
        this.legalEvents = legalEvents;
    }

    public List<String> getLegalEvents() {
        return legalEvents;
    }

    public String getState() {
        return state;
    }
}
