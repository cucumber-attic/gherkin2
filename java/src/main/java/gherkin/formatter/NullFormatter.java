package gherkin.formatter;

import java.util.List;

public class NullFormatter implements Formatter {
    public void scenario(String keyword, String name, int line, String location) {

    }

    public void step(String keyword, String name, int line, String status, List<Argument> arguments, String location) {
    }

    public void flushTable() {

    }

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

    public void row(List<String> row, int line) {

    }

    public void py_string(String string, int line) {

    }

    public void eof() {

    }

    public void syntax_error(String state, String event, List<String> legalEvents, int line) {

    }
}
