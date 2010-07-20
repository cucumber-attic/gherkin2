package gherkin.formatter;

import gherkin.formatter.model.PyString;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Statement;

import java.util.List;

/**
 * This is the interface you should implement if you want your own custom
 * formatter.
 */
public interface Formatter {
    void feature(Statement statement, String uri);

    void background(Statement statement);

    void scenario(Statement statement);

    void scenarioOutline(Statement statement);

    void examples(Statement statement, List<Row> exampleRows);

    void step(Statement statement, List<Row> stepTable, Result result);

    void step(Statement statement, PyString pyString, Result result);

    void eof();

    void table(List<Row> rows);

    void syntaxError(String state, String event, List<String> legalEvents, String uri, int line);
}
