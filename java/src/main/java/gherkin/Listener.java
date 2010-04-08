package gherkin;

import java.io.IOException;
import java.util.List;

public interface Listener {
    void tag(String name, int line) throws Exception;

    void comment(String content, int line) throws Exception;

    void feature(String keyword, String name, int line) throws Exception;

    void background(String keyword, String name, int line) throws Exception;

    void scenario(String keyword, String name, int line) throws Exception;

    void scenario_outline(String keyword, String name, int line) throws Exception;

    void examples(String keyword, String name, int line) throws Exception;

    void step(String keyword, String name, int line) throws IOException, Exception;

    void row(List<String> row, int line) throws Exception;

    void py_string(String string, int line) throws Exception;

    void eof() throws Exception;

    void syntax_error(String state, String event, List<String> legalEvents, int line) throws Exception;
}
