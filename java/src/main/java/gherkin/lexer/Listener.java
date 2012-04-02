package gherkin.lexer;

import java.util.List;

public interface Listener {
    void comment(String comment, Integer line);

    void tag(String tag, Integer line);

    void feature(String keyword, String name, String description, Integer line);

    void background(String keyword, String name, String description, Integer line);

    void scenario(String keyword, String name, String description, Integer line);

    void scenarioOutline(String keyword, String name, String description, Integer line);

    void examples(String keyword, String name, String description, Integer line);

    void step(String keyword, String name, Integer line);

    void row(List<String> cells, Integer line);

    void docString(String contentType, String content, Integer line);

    void eof();
}