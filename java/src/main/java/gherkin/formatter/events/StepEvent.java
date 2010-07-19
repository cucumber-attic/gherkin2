package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.PyString;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Statement;

import java.util.List;

public class StepEvent extends AbstractEvent {
    private final Statement statement;
    private final List<Row> stepTable;
    private final PyString pyString;
    private final Result result;

    public StepEvent(Statement statement, List<Row> stepTable, Result result) {
        this(statement, stepTable, null, result);
    }

    public StepEvent(Statement statement, PyString pyString, Result result) {
        this(statement, null, pyString, result);
    }

    public StepEvent(Statement statement, List<Row> stepTable, PyString pyString, Result result) {
        this.statement = statement;
        this.stepTable = stepTable;
        this.pyString = pyString;
        this.result = result;
    }

    @Override
    public void replay(Formatter formatter) {
        if(stepTable != null) {
            statement.replayStep(formatter, stepTable, result);
        } else {
            statement.replayStep(formatter, pyString, result);
        }
    }
}
