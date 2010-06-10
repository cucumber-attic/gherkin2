package gherkin;

import java.util.List;

public interface Listener {
    void tag(String name, int line);

    void comment(String content, int line);

    void feature(String keyword, String name, String description, int line);

    void background(String keyword, String name, String description, int line);

    void scenario(String keyword, String name, String description, int line);

    void scenarioOutline(String keyword, String name, String description, int line);

    void examples(String keyword, String name, String description, int line);

    void step(String keyword, String name, int line);

    void row(List<String> row, int line);

    void pyString(String string, int line);

    void eof();

    void syntaxError(String state, String event, List<String> legalEvents, int line);
}
