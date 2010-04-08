package gherkin.formatter;

import java.util.List;

public class NullFormatter implements Formatter {
    public void scenario(String keyword, String name, int line, String location) throws Exception {
    }

    public void step(String keyword, String name, int line, String status, List<Argument> arguments, String location) throws Exception {
    }

    public void flushTable() throws Exception {
    }

    public void tag(String name, int line) throws Exception {
    }

    public void comment(String content, int line) throws Exception {
    }

    public void feature(String keyword, String name, int line) throws Exception {
    }

    public void background(String keyword, String name, int line) throws Exception {
    }

    public void scenario(String keyword, String name, int line) throws Exception {
    }

    public void scenario_outline(String keyword, String name, int line) throws Exception {
    }

    public void examples(String keyword, String name, int line) throws Exception {
    }

    public void step(String keyword, String name, int line) throws Exception {
    }

    public void row(List<String> row, int line) throws Exception {
    }

    public void py_string(String string, int line) throws Exception {
    }

    public void eof() throws Exception {
    }

    public void syntax_error(String state, String event, List<String> legalEvents, int line) throws Exception {
    }
}
