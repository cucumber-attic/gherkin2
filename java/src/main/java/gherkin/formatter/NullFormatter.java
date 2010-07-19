package gherkin.formatter;

import gherkin.formatter.model.PyString;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Statement;

import java.util.List;

public class NullFormatter implements Formatter {

    public void feature(Statement statement, String uri) {
        throw new UnsupportedOperationException();
    }

    public void background(Statement statement) {
        throw new UnsupportedOperationException();
    }

    public void scenario(Statement statement) {
        throw new UnsupportedOperationException();
    }

    public void scenarioOutline(Statement statement) {
        throw new UnsupportedOperationException();
    }

    public void examples(Statement statement, List<Row> exampleRows) {
        throw new UnsupportedOperationException();
    }

    public void step(Statement statement, List<Row> stepTable, Result result) {
        throw new UnsupportedOperationException();
    }

    public void step(Statement statement, PyString stepString, Result result) {
        throw new UnsupportedOperationException();
    }

    public void eof() {
        throw new UnsupportedOperationException();
    }

    public void table(List<Row> rows) {
        throw new UnsupportedOperationException();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException();
    }
}
