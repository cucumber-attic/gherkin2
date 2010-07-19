package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Statement;

public class ScenarioOutlineEvent extends AbstractEvent {
    private final Statement statement;

    public ScenarioOutlineEvent(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void replay(Formatter formatter) {
        statement.replayScenarioOutline(formatter);
    }
}
