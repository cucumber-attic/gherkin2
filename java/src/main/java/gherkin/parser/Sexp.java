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

	private int line;
	private String name;
	private String keyword;
	private List<String> row;
	private Events event;

	public Sexp(Events event, String name, int line) {
		this.event = event;
		this.name = name;
		this.line = line;
	}

	public Sexp(Events event, String keyword, String name, int line) {
		this.event = event;
		this.keyword = keyword;
		this.name = name;
		this.line = line;
	}

	public Sexp(Events event, List<String> row, int line) {
		this.event = event;
		this.row = row;
		this.line = line;
	}

	// null event
	public Sexp() {
	}

	public Events getEvent() {
		return this.event;
	}

	public void replay(Listener listener) throws Exception {
		switch (this.event) {
		case ROW:
			listener.row(row, line);
			;
		case TAG:
			listener.tag(name, line);
			;
		case COMMENT: 
			listener.comment(name, line);
			;
		case FEATURE:
			listener.feature(keyword, name, line);
			;
		case BACKGROUND:
			listener.background(keyword, name, line);
			;
		case SCENARIO:
			listener.scenario(keyword, name, line);
			;
		case SCENARIO_OUTLINE:
			listener.scenario_outline(keyword, name, line);
			;
		case EXAMPLES:
			listener.examples(keyword, name, line);
			;
		case STEP:
			listener.step(keyword, name, line);
			;
		case PY_STRING:
			listener.py_string(name, line);
			;
		default:
			break;
		}
	}

	public String getKeyword() {
		return keyword;
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
