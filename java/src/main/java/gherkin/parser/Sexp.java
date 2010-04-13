package gherkin.parser;

import java.util.List;

public class Sexp {

	private int line;
	private String name;
	private String keyword;
	private List<String> row;
	private Events event;

	public Sexp(String name, int line) {
		this.name = name;
		this.line = line;
	}

	public Sexp(String keyword, String name, int line) {
		this.keyword = keyword;
		this.name = name;
		this.line = line;
	}

	public Sexp(List<String> row, int line) {
		this.row = row;
		this.line = line;
	}

	// null event
	public Sexp() {
	}

	public Events getEvent() {
		return this.event;
	}
	
	enum Events{
		ROW
	}

}
