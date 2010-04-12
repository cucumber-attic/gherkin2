package gherkin.parser;

import java.io.IOException;
import java.util.List;

import gherkin.Listener;

public class FilterListener implements Listener {
	
	private final List filters;
	private final Listener listener;

	@SuppressWarnings("unchecked")
	public FilterListener(Listener listener, List filters) {
		this.listener = listener;
		this.filters = filters;
	}
	
	@Override
	public void background(String keyword, String name, int line)
			throws Exception {
//		System.out.printf("background(%s, %s, %d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			listener.background(keyword, name, line);
		}
	}

	@Override
	public void comment(String content, int line) throws Exception {
//		System.out.printf("comment(%s, %d)\n", content, line);
		if (matchLineNumber(line, filters)){
			listener.comment(content, line);
		}
	}

	@Override
	public void eof() throws Exception {
//		System.out.printf("eof");
		listener.eof();
	}

	@Override
	public void examples(String keyword, String name, int line)
			throws Exception {
//		System.out.printf("examples(%s, %s, %d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			listener.examples(keyword, name, line);
		}
	}

	@Override
	public void feature(String keyword, String name, int line) throws Exception {
//		System.out.printf("feature(%s, %s, %d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.feature(keyword, name, line);
		}
	}

	@Override
	public void py_string(String string, int line) throws Exception {
//		System.out.printf("py_string(%s, %d)\n", string, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.py_string(string, line);
		}
	}

	@Override
	public void row(List<String> row, int line) throws Exception {
//		System.out.printf("row(,%d)\n", line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.row(row, line);
		}
	}

	@Override
	public void scenario(String keyword, String name, int line)
			throws Exception {
//		System.out.printf("scenario(%s,%s,%d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.scenario(keyword, name, line);
		}
	}

	@Override
	public void scenario_outline(String keyword, String name, int line)
			throws Exception {
//		System.out.printf("scenario_outline(%s, %s, %d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.scenario_outline(keyword, name, line);
		}
	}

	@Override
	public void step(String keyword, String name, int line) throws IOException,
			Exception {
//		System.out.printf("step(%s, %s, %d)\n", keyword, name, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.step(keyword, name, line);
		}
	}

	@Override
	public void syntax_error(String state, String event,
			List<String> legalEvents, int line) throws Exception {
//		System.out.printf("syntax_error(%s, %s, ?, %d)\n", state, event, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.syntax_error(state, event, legalEvents, line);
		}
	}

	@Override
	public void tag(String name, int line) throws Exception {
//		System.out.printf("tag(%s, %d)\n", name, line);
		if (matchLineNumber(line, filters)){
			System.out.println("matched line number");
			listener.tag(name, line);
		}
	}
	
	private boolean matchLineNumber(int line, List filters) {
		for (Object filter : filters) {
			if (filter.equals(line)){
				return true;
			}
		}
		return false;
	}

}
