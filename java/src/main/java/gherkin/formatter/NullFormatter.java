package gherkin.formatter;

import gherkin.formatter.model.PyString;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Statement;

import java.util.List;

public class NullFormatter implements Formatter {
    public void feature(Statement statement, String uri) {
    }

    public void background(Statement statement) {
    }

    public void scenario(Statement statement) {
    }

    public void scenarioOutline(Statement statement) {
    }

    public void examples(Statement statement, List<Row> exampleRows) {
    }

    public void step(Statement statement, List<Row> stepTable, Result result) {
    }

    public void step(Statement statement, PyString stepString, Result result) {
    }

    public void eof() {
    }

    public void table(List<Row> rows) {
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
    }
}
