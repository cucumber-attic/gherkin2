package gherkin;

import java.util.List;

public class Parser implements Listener {
    private final Listener listener;
    private final boolean raiseOnError;

    public Parser(Listener listener, boolean raiseOnError) {
        this.listener = listener;
        this.raiseOnError = raiseOnError;
    }

    public void tag(String name, int line) {
        listener.tag(name, line);
    }

    public void py_string(int startCol, String string, int line) {
        listener.py_string(startCol, string, line);
    }

    public void feature(String keyword, String name, int line) {
        listener.feature(keyword, name, line);
    }

    public void background(String keyword, String name, int line) {
        listener.background(keyword, name, line);
    }

    public void scenario(String keyword, String name, int line) {
        listener.scenario(keyword, name, line);
    }

    public void scenario_outline(String keyword, String name, int line) {
        listener.scenario_outline(keyword, name, line);
    }

    public void examples(String keyword, String name, int line) {
        listener.examples(keyword, name, line);
    }

    public void step(String keyword, String name, int line) {
        listener.step(keyword, name, line);
    }

    public void comment(String content, int line) {
        listener.comment(content, line);
    }

    public void table(List<List<String>> rows, int line) {
        listener.table(rows, line);
    }
}
