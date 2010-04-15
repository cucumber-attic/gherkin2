package gherkin.parser;

import gherkin.Listener;

import java.util.List;

public class Sexp {
	
	enum Events{
		ROW, TAG, COMMENT, 
		FEATURE, BACKGROUND, SCENARIO, 
		SCENARIO_OUTLINE, EXAMPLES, STEP, 
		PY_STRING
	}

	private final Events event;
    private final String keyword;
    private final String name;
    private final List<String> row;
    private final int line;

	public Sexp(Events event, String name, int line) {
        this(event, null, name, line);
	}

	public Sexp(Events event, String keyword, String name, int line) {
        this(event, keyword, name, null, line);
	}

	public Sexp(Events event, List<String> row, int line) {
		this(event, null, null, row, line);
	}

    private Sexp(Events event, String keyword, String name, List<String> row, int line) {
        this.event = event;
        this.keyword = keyword;
        this.name = name;
        this.row = row;
        this.line = line;
    }

	public Events getEvent() {
		return this.event;
	}

	public void replay(Listener listener) throws Exception {
		switch (this.event) {
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
			listener.feature(keyword, name, line);
            break;
		case BACKGROUND:
			listener.background(keyword, name, line);
            break;
		case SCENARIO:
			listener.scenario(keyword, name, line);
            break;
		case SCENARIO_OUTLINE:
			listener.scenario_outline(keyword, name, line);
            break;
		case EXAMPLES:
			listener.examples(keyword, name, line);
            break;
		case STEP:
			listener.step(keyword, name, line);
            break;
		case PY_STRING:
			listener.py_string(name, line);
            break;
		default:
			throw new RuntimeException("Bad event");
		}
	}

	public Integer getLine() {
		return line;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Sexp [event=" + event + ", keyword=" + keyword + ", line="
				+ line + ", name=" + name + ", row=" + row + "]";
	}

}
