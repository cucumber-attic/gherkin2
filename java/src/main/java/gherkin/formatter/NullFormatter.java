package gherkin.formatter;

import java.util.List;

public class NullFormatter implements Formatter {
    public void scenario(String keyword, String name, String description, int line, String location) {
    }

    public void step(String keyword, String name, int line, String status, Throwable thrown, List<Argument> arguments, String sourceLocation) {
    }

    public void flushTable() {
    }

    public void tag(String name, int line) {
    }

    public void comment(String content, int line) {
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

    public void row(List<String> row, int line) {
    }

    public void pyString(String string, int line) {
    }

    public void eof() {
    }

    public void syntaxError(String state, String event, List<String> legalEvents, int line) {
    }
}
