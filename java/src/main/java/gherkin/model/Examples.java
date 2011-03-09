package gherkin.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Examples extends DescribedStatement implements RowContainer {
    private final ScenarioOutline scenarioOutline;
    private List<Row> rows;
    private Row headerRow;
    private List<ExampleScenario> exampleScenarios = new ArrayList<ExampleScenario>();
    public Examples next;

    public Examples(ScenarioOutline scenarioOutline, List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line, List<Row> rows) {
        super(comments, tags, keyword, name, description, line);
        this.scenarioOutline = scenarioOutline;
        this.rows = rows;
    }

    public Examples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line, List<Row> rows) {
        this(null, comments, tags, keyword, name, description, line, rows);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.examples(this);
    }

    @Deprecated
    public List<Row> getRows() {
        return rows;
    }

    public void addRow(Row row) {
        if(headerRow == null) {
            headerRow = row;
        } else {
            addScenario(row);
        }
        rows.add(row);
    }

    private void addScenario(Row row) {
        ExampleScenario scenario = scenarioOutline.createScenario(headerRow, row, this);
        exampleScenarios.add(scenario);
    }

    @Deprecated
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void accept(Visitor v) {
        for (ExampleScenario exampleScenario : exampleScenarios) {
            exampleScenario.accept(v);
        }
        v.visitExamples(this);
    }
}
