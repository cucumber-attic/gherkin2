package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Statement;

public class ScenarioEvent extends AbstractEvent {
    private final Statement statement;

    public ScenarioEvent(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void replay(Formatter formatter) {
        statement.replayScenario(formatter);
    }
}
