package gherkin.formatter.model;

import gherkin.formatter.visitors.Next;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExamplesTableRow extends Row {
    private static final long serialVersionUID = 1L;

    private final String id;

    public ExamplesTableRow(List<Comment> comments, List<String> cells, Integer line, String id) {
        super(comments, cells, line);
        this.id = id;
    }

    @Override
    public DiffType getDiffType() {
        return DiffType.NONE;
    }

    public String getId() {
        return id;
    }

    public Scenario createScenario(List<Step> outlineSteps, ExamplesTableRow headerRow, List<Tag> tags, String name, String description, Background background) {
        Scenario scenario = new Scenario(getComments(), tags, "SOME KEYWORD", name, description, getLine(), getId(), background);
        for (Step outlineStep : outlineSteps) {
            scenario.addStep(createExampleStep(outlineStep, headerRow));
        }
        return scenario;
    }

    private Step createExampleStep(Step step, ExamplesTableRow headerRow) {
        Set<Integer> matchedColumns = new HashSet<Integer>();
        List<String> headerCells = headerRow.getCells();
        List<String> exampleCells = getCells();

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

    @Override
    public void accept(Visitor visitor, Next bakctrack) {

    }
}
