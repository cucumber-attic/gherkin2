package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScenarioOutline extends TagStatement implements StepContainer {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused") // it's in the JSON
    private final String type = "scenario_outline";
    private final List<Step> steps = new ArrayList<Step>();
    private final Background background;
    private final List<Scenario> scenarios = new ArrayList<Scenario>();

    @SuppressWarnings("unused") // Legacy API
    @Deprecated
    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
        this(comments, tags, keyword, name, description, line, id, null);
    }

    public ScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id, Background background) {
        super(comments, tags, keyword, name, description, line, id);
        this.background = background;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.scenarioOutline(this);
    }

    @Override
    public List<Step> getSteps() {
        List<Step> result = new ArrayList<Step>();
        if (background != null) {
            result.addAll(background.getSteps());
        }
        result.addAll(steps);
        return Collections.unmodifiableList(result);
    }

    @Override
    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public void addScenario(ExamplesTableRow header, ExamplesTableRow example, List<Tag> tags) {
        String idTODO = null; // TODO
        Scenario scenario = new Scenario(getComments(), getTags(), getKeyword(), getName(), getDescription(), example.getLine(), idTODO, background);
        for (Step outlineStep : steps) {
            scenario.addStep(createExampleStep(outlineStep, header, example));
        }
        scenarios.add(scenario);
    }

    @Override
    public Collection<Scenario> getScenarios() {
        return scenarios;
    }

    private Step createExampleStep(Step step, ExamplesTableRow headerRow, ExamplesTableRow exampleRow) {
        Set<Integer> matchedColumns = new HashSet<Integer>();
        List<String> headerCells = headerRow.getCells();
        List<String> exampleCells = exampleRow.getCells();

        // Create a step with replaced tokens
        String name = replaceTokens(matchedColumns, headerCells, exampleCells, step.getName());

        return new Step(
                step.getComments(),
                step.getKeyword(),
                name,
                step.getLine(),
                rowsWithTokensReplaced(step.getRows(), headerCells, exampleCells, matchedColumns),
                docStringWithTokensReplaced(step.getDocString(), headerCells, exampleCells, matchedColumns)
        );
    }

    private String replaceTokens(Set<Integer> matchedColumns, List<String> headerCells, List<String> exampleCells, String text) {
        for (int i = 0; i < headerCells.size(); i++) {
            String headerCell = headerCells.get(i);
            String value = exampleCells.get(i);
            String token = "<" + headerCell + ">";
            if (text.contains(token)) {
                text = text.replace(token, value);
                matchedColumns.add(i);
            }
        }
        return text;
    }

    private List<DataTableRow> rowsWithTokensReplaced(List<DataTableRow> rows, List<String> headerCells, List<String> exampleCells, Set<Integer> matchedColumns) {
        if (rows != null) {
            List<DataTableRow> newRows = new ArrayList<DataTableRow>(rows.size());
            for (Row row : rows) {
                List<String> newCells = new ArrayList<String>(row.getCells().size());
                for (String cell : row.getCells()) {
                    newCells.add(replaceTokens(matchedColumns, headerCells, exampleCells, cell));
                }
                newRows.add(new DataTableRow(row.getComments(), newCells, row.getLine()));
            }
            return newRows;
        } else {
            return null;
        }
    }

    private DocString docStringWithTokensReplaced(DocString docString, List<String> headerCells, List<String> exampleCells, Set<Integer> matchedColumns) {
        if (docString != null) {
            String docStringValue = replaceTokens(matchedColumns, headerCells, exampleCells, docString.getValue());
            return new DocString(docString.getContentType(), docStringValue, docString.getLine());
        } else {
            return null;
        }
    }

}
