package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Statement;

public class BackgroundEvent extends AbstractEvent {
    private final Statement statement;

    public BackgroundEvent(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void replay(Formatter formatter) {
        statement.replayBackground(formatter);
    }
}
