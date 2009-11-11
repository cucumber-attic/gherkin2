package gherkin;

import gherkin.lexer.En;

import java.io.*;
import java.util.*;

public class Parser implements Listener {
    private List<List<String>> transitionTable;
    private Map<String, Integer> states = new HashMap<String, Integer>();
    private Map<String, Integer> events = new HashMap<String, Integer>();
    private String state;
    private Listener listener;
    private boolean throwOnError;
    private final String machinePath;

    public Parser(Listener listener) {
        this(listener, true);
    }

    public Parser(Listener listener, boolean throwOnError) {
        this(listener, throwOnError, "root");
    }

    public Parser(Listener listener, boolean throwOnError, String state) {
        this.listener = listener;
        this.throwOnError = throwOnError;
        this.state = state;
        this.machinePath = "/gherkin/parser/" + state + ".txt";
        initializeMachine();
    }

    private void initializeMachine() {
        Listener stateMachineReader = new Listener() {
            public void tag(String name, int line) {
            }

            public void comment(String content, int line) {
            }

            public void feature(String keyword, String name, int line) {
            }

            public void background(String keyword, String name, int line) {
            }

            public void scenario(String keyword, String name, int line) {
            }

            public void scenario_outline(String keyword, String name, int line) {
            }

            public void examples(String keyword, String name, int line) {
            }

            public void step(String keyword, String name, int line) {
            }

            public void py_string(int startCol, String string, int line) {
            }

            public void syntax_error(String name, String event, List<String> strings, int line) {
            }

            public void table(List<List<String>> rows, int line) {
                transitionTable = rows;
            }
        };
        Lexer l = new En(stateMachineReader);
        try {
            l.scan(readResourceAsString(machinePath));
            initStates();
            initEvents();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't read " + machinePath, e);
        }
    }

    public void tag(String name, int line) {
        if (event("tag", line))
            listener.tag(name, line);
    }

    public void py_string(int startCol, String string, int line) {
        if (event("py_string", line))
            listener.py_string(startCol, string, line);
    }

    public void feature(String keyword, String name, int line) {
        if (event("feature", line))
            listener.feature(keyword, name, line);
    }

    public void background(String keyword, String name, int line) {
        if (event("background", line))
            listener.background(keyword, name, line);
    }

    public void scenario(String keyword, String name, int line) {
        if (event("scenario", line))
            listener.scenario(keyword, name, line);
    }

    public void scenario_outline(String keyword, String name, int line) {
        if (event("scenario_outline", line))
            listener.scenario_outline(keyword, name, line);
    }

    public void examples(String keyword, String name, int line) {
        if (event("examples", line))
            listener.examples(keyword, name, line);
    }

    public void step(String keyword, String name, int line) {
        if (event("step", line))
            listener.step(keyword, name, line);
    }

    public void comment(String content, int line) {
        if (event("comment", line))
            listener.comment(content, line);
    }

    public void table(List<List<String>> rows, int line) {
        if (event("table", line))
            listener.table(rows, line);
    }

    public void syntax_error(String name, String event, List<String> strings, int line) {
    }

    private void initStates() {
        int i = 0;
        for (List<String> row : transitionTable) {
            if (i > 0) {
                states.put(row.get(0), i);
            }
            i++;
        }
    }

    private void initEvents() {
        int i = 0;
        for (String event : transitionTable.get(0)) {
            if (i > 0) {
                events.put(event, i);
            }
            i++;
        }
    }

    // Return false if there was a parse error.
    private boolean event(String event, int line) {
        int row = stateIdx(state);
        int col = eventIdx(event);
        String newState = transitionTable.get(row).get(col);
        if (newState.equals("E")) {
            if (throwOnError) {
                throw new ParseError("Parse error on line " + line + ". Found " + event + " when expecting one of: " + join(expected(), ", ") + ".");
            } else {
                listener.syntax_error(state, event, expected(), line);
                return false;
            }
        } else if (newState.equals("-")) {
        } else {
            state = newState;
        }
        return true;
    }

    private List<String> expected() {
        int row = stateIdx(state);
        List<String> expected = new ArrayList<String>();
        int i = -1;
        for (String state : transitionTable.get(row)) {
            i++;
            if (i == 0) {
                continue;
            }
            if (!state.equals("E")) {
                expected.add(transitionTable.get(0).get(i));
            }
        }
        Collections.sort(expected);
        return expected;
    }

    private int stateIdx(String state) {
        return states.get(state);
    }

    private int eventIdx(String state) {
        return events.get(state);
    }

    private static String join(List<String> strings, String separator) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String s : strings) {
            if (i != 0) sb.append(separator);
            sb.append(s);
            i++;
        }
        return sb.toString();
    }

    private static String readResourceAsString(String filePath) throws IOException {
        Reader machine = new InputStreamReader(Parser.class.getResourceAsStream(filePath));

        final char[] buffer = new char[0x10000];
        StringBuilder out = new StringBuilder();
        int read = 0;
        do {
            read = machine.read(buffer, 0, buffer.length);
            if (read > 0) {
                out.append(buffer, 0, read);
            }
        } while (read >= 0);
        return out.toString();
    }
}
