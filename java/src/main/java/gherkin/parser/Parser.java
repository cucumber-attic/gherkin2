package gherkin.parser;

import gherkin.I18n;
import gherkin.formatter.Formatter;
import gherkin.lexer.I18nLexer;
import gherkin.lexer.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Listener {
    List<Machine> machines = new ArrayList<Machine>();

    private final boolean throwOnError;
    private final String machineName;
    private FormatterListener listener;
    private I18nLexer lexer;
    private String featureURI;
    private int lineOffset;
    private final Formatter formatter;

    public Parser(Formatter formatter) {
        this(formatter, true);
    }

    public Parser(Formatter formatter, boolean throwOnError) {
        this(formatter, throwOnError, "root");
    }

    public Parser(Formatter formatter, boolean throwOnError, String machineName) {
        this(formatter, throwOnError, machineName, false);
    }

    public Parser(Formatter formatter, boolean throwOnError, String machineName, boolean forceRubyDummy) {
        this.formatter = formatter;
        this.listener = new FormatterListener(formatter);
        this.throwOnError = throwOnError;
        this.machineName = machineName;
        this.lexer = new I18nLexer(this, forceRubyDummy);
    }

    /**
     * @param featureURI the URI where the gherkin originated from. Typically a file path.
     * @param lineOffset the line offset within the uri document the gherkin was taken from. Typically 0.
     */
    public void parse(String gherkin, String featureURI, int lineOffset) {
        formatter.uri(featureURI);
        this.featureURI = featureURI;
        this.lineOffset = lineOffset;
        pushMachine(machineName);
        lexer.scan(gherkin);
        popMachine();
    }

    public I18n getI18nLanguage() {
        return lexer.getI18nLanguage();
    }

    private void pushMachine(String machineName) {
        machines.add(new Machine(this, machineName, featureURI));
    }

    private void popMachine() {
        machines.remove(machines.size() - 1);
    }

    public void tag(String tag, int line) {
        if (event("tag", line)) {
            listener.tag(tag, line);
        }
    }

    public void pyString(String string, int line) {
        if (event("py_string", line)) {
            listener.pyString(string, line);
        }
    }

    public void feature(String keyword, String name, String description, int line) {
        if (event("feature", line)) {
            listener.feature(keyword, name, description, line);
        }
    }

    public void background(String keyword, String name, String description, int line) {
        if (event("background", line)) {
            listener.background(keyword, name, description, line);
        }
    }

    public void scenario(String keyword, String name, String description, int line) {
        if (event("scenario", line)) {
            listener.scenario(keyword, name, description, line);
        }
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        if (event("scenario_outline", line)) {
            listener.scenarioOutline(keyword, name, description, line);
        }
    }

    public void examples(String keyword, String name, String description, int line) {
        if (event("examples", line)) {
            listener.examples(keyword, name, description, line);
        }
    }

    public void step(String keyword, String name, int line) {
        if (event("step", line)) {
            listener.step(keyword, name, line);
        }
    }

    public void comment(String comment, int line) {
        if (event("comment", line)) {
            listener.comment(comment, line);
        }
    }

    public void row(List<String> cells, int line) {
        if (event("row", line)) {
            listener.row(cells, line);
        }
    }

    public void eof() {
        if (event("eof", 1)) {
            listener.eof();
        }
    }

    public void syntaxError(String state, String event, List<String> legalEvents, int line) {
        throw new UnsupportedOperationException();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new RuntimeException("Didn't expect this");
    }

    private boolean event(String event, int line) {
        try {
            machine().event(event, line);
            return true;
        } catch (ParseError e) {
            if (throwOnError) {
                throw e;
            } else {
                int l = lineOffset + line;
                listener.syntaxError(e.getState(), event, e.getLegalEvents(), featureURI, l);
                return false;
            }
        }
    }

    private Machine machine() {
        return machines.get(machines.size() - 1);
    }

    private static class Machine {
        private static final Pattern PUSH = Pattern.compile("push\\((.+)\\)");
        private static final Map<String, Map<String, Map<String, String>>> TRANSITION_MAPS = new HashMap<String, Map<String, Map<String, String>>>();

        private final Parser parser;
        private final String name;
        private final String uri;
        private String state;
        private Map<String, Map<String, String>> transitionMap;

        public Machine(Parser parser, String name, String uri) {
            if (uri == null) {
                throw new NullPointerException("uri");
            }
            this.parser = parser;
            this.name = name;
            this.state = name;
            this.uri = uri;
            this.transitionMap = transitionMap(name);
        }

        public void event(String event, int line) {
            Map<String, String> states = transitionMap.get(state);
            if (states == null) {
                throw new RuntimeException("Unknown getState: " + state + " for machine " + name);
            }
            String newState = states.get(event);
            if (newState == null) {
                throw new RuntimeException("Unknown transition: " + event + " among " + states + " for machine " + name + " in getState " + state);
            }
            if ("E".equals(newState)) {
                throw new ParseError(state, event, expectedEvents(), uri, line);
            } else {
                Matcher push = PUSH.matcher(newState);
                if (push.matches()) {
                    parser.pushMachine(push.group(1));
                    parser.event(event, line);
                } else if ("pop()".equals(newState)) {
                    parser.popMachine();
                    parser.event(event, line);
                } else {
                    state = newState;
                }
            }
        }

        private List<String> expectedEvents() {
            List<String> result = new ArrayList<String>();
            for (String event : transitionMap.get(state).keySet()) {
                if (!transitionMap.get(state).get(event).equals("E")) {
                    result.add(event);
                }
            }
            Collections.sort(result);
            result.remove("eof");
            return result;
        }

        private Map<String, Map<String, String>> transitionMap(String name) {
            Map<String, Map<String, String>> map = TRANSITION_MAPS.get(name);
            if (map == null) {
                map = buildTransitionMap(name);
                TRANSITION_MAPS.put(name, map);
            }
            return map;
        }

        private Map<String, Map<String, String>> buildTransitionMap(String name) {
            Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
            List<List<String>> transitionTable = new StateMachineReader(name).transitionTable();
            List<String> events = transitionTable.get(0).subList(1, transitionTable.get(0).size());
            for (List<String> actions : transitionTable.subList(1, transitionTable.size())) {
                Map<String, String> transitions = new HashMap<String, String>();
                int col = 1;
                for (String event : events) {
                    transitions.put(event, actions.get(col++));
                }
                String state = actions.get(0);
                result.put(state, transitions);
            }
            return result;
        }
    }
}
