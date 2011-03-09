package gherkin;

import gherkin.model.Feature;
import gherkin.model.FeatureBuilder;
import gherkin.lexer.I18nLexer;
import gherkin.lexer.Lexer;
import gherkin.lexer.i18n.EN;
import gherkin.util.FixJava;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verifies that events come in the proper order.
 */
public class GherkinParser implements FeatureParser, Listener {
    List<Machine> machines = new ArrayList<Machine>();

    private final String machineName;
    private final FeatureBuilder featureBuilder;
    private final I18nLexer lexer;
    private String featureURI;
    private int lineOffset;

    public GherkinParser() {
        this("root");
    }

    public GherkinParser(String machineName) {
        this.featureBuilder = new FeatureBuilder();
        this.machineName = machineName;
        this.lexer = new I18nLexer(this);
    }

    /**
     * @param featureURI the URI where the gherkin originated from. Typically a file path.
     * @param lineOffset the line offset within the uri document the gherkin was taken from. Typically 0.
     */
    public Feature parse(String gherkin, String featureURI, int lineOffset) {
        this.featureURI = featureURI;
        this.lineOffset = lineOffset;
        pushMachine(machineName);
        lexer.scan(gherkin);
        popMachine();
        return featureBuilder.getFeature();
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
            featureBuilder.tag(tag, line);
        }
    }

    public void pyString(String string, int line) {
        if (event("py_string", line)) {
            featureBuilder.pyString(string, line);
        }
    }

    public void feature(String keyword, String name, String description, int line) {
        if (event("feature", line)) {
            featureBuilder.feature(keyword, name, description, line);
        }
    }

    public void background(String keyword, String name, String description, int line) {
        if (event("background", line)) {
            featureBuilder.background(keyword, name, description, line);
        }
    }

    public void scenario(String keyword, String name, String description, int line) {
        if (event("scenario", line)) {
            featureBuilder.scenario(keyword, name, description, line);
        }
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        if (event("scenario_outline", line)) {
            featureBuilder.scenarioOutline(keyword, name, description, line);
        }
    }

    public void examples(String keyword, String name, String description, int line) {
        if (event("examples", line)) {
            featureBuilder.examples(keyword, name, description, line);
        }
    }

    public void step(String keyword, String name, int line) {
        if (event("step", line)) {
            featureBuilder.step(keyword, name, line);
        }
    }

    public void comment(String comment, int line) {
        if (event("comment", line)) {
            featureBuilder.comment(comment, line);
        }
    }

    public void row(List<String> cells, int line) {
        if (event("row", line)) {
            featureBuilder.row(cells, line);
        }
    }

    public void eof() {
        if (event("eof", 1)) {
            featureBuilder.eof();
        }
    }


    private boolean event(String event, int line) {
        try {
            machine().event(event, line);
            return true;
        } catch (ParseError e) {
            e.setLine(lineOffset + line);
            throw e;
        }
    }

    private Machine machine() {
        return machines.get(machines.size() - 1);
    }

    private static class Machine {
        private static final Pattern PUSH = Pattern.compile("push\\((.+)\\)");
        private static final Map<String, Map<String, Map<String, String>>> TRANSITION_MAPS = new HashMap<String, Map<String, Map<String, String>>>();

        private final GherkinParser gherkinParser;
        private final String name;
        private final String uri;
        private String state;
        private Map<String, Map<String, String>> transitionMap;

        public Machine(GherkinParser gherkinParser, String name, String uri) {
            if (uri == null) {
                throw new NullPointerException("uri");
            }
            this.gherkinParser = gherkinParser;
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
                    gherkinParser.pushMachine(push.group(1));
                    gherkinParser.event(event, line);
                } else if ("pop()".equals(newState)) {
                    gherkinParser.popMachine();
                    gherkinParser.event(event, line);
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

    public static class ParseError extends RuntimeException {
        private final String state;
        private final String event;
        private final List<String> legalEvents;
        private final String uri;
        private int line;
    
        public ParseError(String state, String event, List<String> legalEvents, String uri, int line) {
            this.state = state;
            this.event = event;
            this.legalEvents = legalEvents;
            this.uri = uri;
            this.line = line;
        }
    
        public List<String> getLegalEvents() {
            return legalEvents;
        }
    
        public String getState() {
            return state;
        }
        
        public String getMessage() {
            return "Parse error at " + uri + ":" + line + ". Found " + event + " when expecting one of: " + FixJava.join(legalEvents, ", ") + ". (Current getState: " + state + ").";
        }
    
        public void setLine(int line) {
            this.line = line;
        }
    }

    public static class StateMachineReader implements Listener {
        private final String machinePath;
        private List<List<String>> transitionTable;
    
        public StateMachineReader(String name) {
            machinePath = "/gherkin/parser/" + name + ".txt";
        }
    
        public List<List<String>> transitionTable() {
            transitionTable = new ArrayList<List<String>>();
            Lexer lexer = new EN(this);
            lexer.scan(FixJava.readResource(machinePath));
            return transitionTable;
        }
    
        public void tag(String tag, int line) {
        }
    
        public void comment(String comment, int line) {
        }
    
        public void feature(String keyword, String name, String description, int line) {
        }
    
        public void background(String keyword, String name, String description, int line) {
        }
    
        public void scenario(String keyword, String name, String description, int line) {
        }
    
        public void scenarioOutline(String keyword, String name, String description, int line) {
        }
    
        public void examples(String keyword, String name, String description, int line) {
        }
    
        public void step(String keyword, String name, int line) {
        }
    
        public void pyString(String string, int line) {
        }
    
        public void eof() {
        }
    
        public void row(List<String> cells, int line) {
            transitionTable.add(cells);
        }
    }
}
