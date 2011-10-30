package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class ScenarioOutline extends TagStatement {
    private final String type = "scenario_outline";

    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line, String id) {
        super(comments, tags, keyword, name, description, line, id);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenarioOutline(this);
    }
}
