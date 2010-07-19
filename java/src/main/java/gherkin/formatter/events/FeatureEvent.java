package gherkin.formatter.events;

import gherkin.formatter.AbstractEvent;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Statement;

public class FeatureEvent extends AbstractEvent {
    private final Statement statement;
    private final String uri;

    public FeatureEvent(Statement statement, String uri) {
        this.statement = statement;
        this.uri = uri;
    }

    @Override
    public void replay(Formatter formatter) {
        statement.replayFeature(formatter, uri);
    }
}
