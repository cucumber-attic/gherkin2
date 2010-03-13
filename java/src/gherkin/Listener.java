package gherkin;

import java.util.List;

public interface Listener {
    void tag(String name, int line);

    void comment(String content, int line);

    void feature(String keyword, String name, int line);

    void background(String keyword, String name, int line);

    void scenario(String keyword, String name, int line);

    void scenario_outline(String keyword, String name, int line);

    void examples(String keyword, String name, int line);

    void step(String keyword, String name, int line);

    void row(List<String> rows, int line);

    void py_string(String string, int line);

    void syntax_error(String state, String event, List<String> legalEvents, int line);
}
