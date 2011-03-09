package gherkin.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class ScenarioOutline extends FeatureElement {
    private final String type = "scenario_outline";
    private final List<Examples> all_examples = new ArrayList<Examples>();
    public Examples examples;

    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void accept(Visitor v) {
        for (Examples examples : all_examples) {
            examples.accept(v);
        }
        v.visitScenarioOutline(this);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenarioOutline(this);
    }

    public Examples newExamples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        Examples examples = new Examples(this, comments, tags, keyword, name, description, line, new ArrayList<Row>());
        this.all_examples.add(examples);
        return examples;
    }

    public ExampleScenario createScenario(Row headerRow, Row row, Examples examples) {
        String name = row.toString();
        ExampleScenario scenario = new ExampleScenario(row.getComments(), examples.getTags(), examples.getKeyword(), name, null, row.getLine());
        for (Step step : getSteps()) {
            scenario.newStep(step.createExampleStep(headerRow, row));
        }
        return scenario;
    }
}
