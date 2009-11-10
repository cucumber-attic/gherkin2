package gherkin;

import java.util.List;

public interface Listener {
    void tag(String name, int line);
    void py_string(int startCol, String string, int line);
    void feature(String keyword, String name, int line);
    void background(String keyword, String name, int line);
    void scenario(String keyword, String name, int line);
    void scenario_outline(String keyword, String name, int line);
    void examples(String keyword, String name, int line);
    void step(String keyword, String name, int line);
    void comment(String content, int number);
    void table(List<List<String>> rows, int currentLine);
}
