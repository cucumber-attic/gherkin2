package gherkin.parser;

import gherkin.Listener;

import java.util.List;
import java.util.regex.Pattern;

public class Event {
    enum Type {
        ROW, TAG, COMMENT,
        FEATURE, BACKGROUND, SCENARIO,
        SCENARIO_OUTLINE, EXAMPLES, STEP,
        PY_STRING
    }

    private final Type type;
    private final String keyword;
    private final String name;
    private final String description;
    private final List<String> row;
    private final int line;

    public Event(Type type, String name, int line) {
        this(type, null, name, null, null, line);
    }

    public Event(Type type, String keyword, String name, int line) {
        this(type, keyword, name, null, null, line);
    }

    public Event(Type type, String keyword, String name, String description, int line) {
        this(type, keyword, name, description, null, line);
    }

    public Event(Type type, List<String> row, int line) {
        this(type, null, null, null, row, line);
    }

    private Event(Type type, String keyword, String name, String description, List<String> row, int line) {
        this.type = type;
        this.keyword = keyword;
        this.name = name;
        this.description = description;
        this.row = row;
        this.line = line;
    }

    public Type getType() {
        return type;
    }

    public void replay(Listener listener) {
        switch (type) {
            case ROW:
                listener.row(row, line);
                break;
            case TAG:
                listener.tag(name, line);
                break;
            case COMMENT:
                listener.comment(name, line);
                break;
            case FEATURE:
                listener.feature(keyword, name, description, line);
                break;
            case BACKGROUND:
                listener.background(keyword, name, description, line);
                break;
            case SCENARIO:
                listener.scenario(keyword, name, description, line);
                break;
            case SCENARIO_OUTLINE:
                listener.scenarioOutline(keyword, name, description, line);
                break;
            case EXAMPLES:
                listener.examples(keyword, name, description, line);
                break;
            case STEP:
                listener.step(keyword, name, line);
                break;
            case PY_STRING:
                listener.pyString(name, line);
                break;
        }
    }

    public boolean matches(Pattern filter) {
        switch (type) {
            case FEATURE:
            case BACKGROUND:
            case SCENARIO:
            case SCENARIO_OUTLINE:
            case EXAMPLES:
                return filter.matcher(name).matches();
            default:
                return false;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getLine() {
        return line;
    }
}
