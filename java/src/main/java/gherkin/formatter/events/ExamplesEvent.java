package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Statement;

import java.util.List;

public class ExamplesEvent extends AbstractEvent {
    private final Statement statement;
    private final List<Row> examplesRows;

    public ExamplesEvent(Statement statement, List<Row> examplesRows) {
        this.statement = statement;
        this.examplesRows = examplesRows;
    }

    @Override
    public void replay(Formatter formatter) {
        statement.replayExamples(formatter, examplesRows);
    }
}
