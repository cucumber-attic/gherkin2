package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import gherkin.formatter.visitors.Next;

import java.util.ArrayList;
import java.util.List;

public class Examples extends TagStatement implements Visitable {
    private static final long serialVersionUID = 1L;

    private List<ExamplesTableRow> rows;

    public void accept(Visitor visitor, Next next) throws Exception {
        visitor.visitExamples(this);
        for (ExamplesTableRow row : rows) {
            row.accept(visitor, next);
        }
    }

    public static class ExamplesBuilder implements Builder {
        private final List<Comment> comments;
        private final List<Tag> tags;
        private final String keyword;
        private final String name;
        private final String description;
        private final Integer line;
        private final String id;
        private List<ExamplesTableRow> rows = new ArrayList<ExamplesTableRow>();
        ;

        public ExamplesBuilder(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id) {
            this.comments = comments;
            this.tags = tags;
            this.keyword = keyword;
            this.name = name;
            this.description = description;
            this.line = line;
            this.id = id;
        }

        public void row(List<Comment> comments, List<String> cells, Integer line, String id) {
            rows.add(new ExamplesTableRow(comments, cells, line, id));
        }

        public void replay(Formatter formatter) {
            new Examples(comments, tags, keyword, name, description, line, id, rows).replay(formatter);
        }

        @Override
        public void populateStepContainer(StepContainer stepContainer) {
            Examples examples = new Examples(comments, tags, keyword, name, description, line, id, rows);
            stepContainer.addExamples(examples);
        }

        public void docString(DocString docString) {
            throw new IllegalStateException("Can't have DocString in Examples");
        }
    }

    public Examples(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, Integer line, String id, List<ExamplesTableRow> rows) {
        super(comments, tags, keyword, name, description, line, id);
        this.rows = rows;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.examples(this);
    }

    public List<ExamplesTableRow> getRows() {
        return rows;
    }

    public void setRows(List<ExamplesTableRow> rows) {
        this.rows = rows;
    }

    public List<Scenario> createScenarios(List<Step> outlineSteps, Background background) {
        List<Scenario> scenarios = new ArrayList<Scenario>();
        for (int i = 1; i < rows.size(); i++) {
            scenarios.add(rows.get(i).createScenario(outlineSteps, rows.get(0), getTags(), getName(), getDescription(), background));
        }
        return scenarios;
    }
}
