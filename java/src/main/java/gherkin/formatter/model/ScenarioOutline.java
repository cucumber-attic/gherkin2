package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class ScenarioOutline extends FeatureElement {
    private final String type = "scenario_outline";
    private final List<Examples> examples = new ArrayList<Examples>();

    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenarioOutline(this);
    }

    public Examples newExamples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        Examples examples = new Examples(comments, tags, keyword, name, description, line, new ArrayList<Row>());
        this.examples.add(examples);
        return examples;
    }
}
