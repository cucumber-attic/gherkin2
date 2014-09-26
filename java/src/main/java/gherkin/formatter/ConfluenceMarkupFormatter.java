package gherkin.formatter;

import gherkin.formatter.model.*;
import gherkin.util.Mapper;

import java.util.ArrayList;
import java.util.List;

import static gherkin.formatter.ConfluenceMarkup.Formats.*;
import static gherkin.util.FixJava.join;
import static gherkin.util.FixJava.map;

/**
 * This class pretty prints feature files in Confluence Markup (tested with v4.1.22).
 * This class prints "Feature", "Background", and "scenarios" with their tags if any.
 */
public class ConfluenceMarkupFormatter implements Formatter {


    private final NiceAppendable out;
    private final Options options;

    private ConfluenceMarkup formats;
    private List<Step> steps = new ArrayList<Step>();
    private DescribedStatement statement;
    private Mapper<Tag, String> tagNameMapper = new Mapper<Tag, String>() {
        @Override
        public String map(Tag tag) {
            return getFormat(BOLD).text(getFormat(ITALICS).text(
                    tag.getName().replace("@", "")));
        }
    };

    public ConfluenceMarkupFormatter(Appendable out, Options options) {
        this.out = new NiceAppendable(out);
        this.options = options;
        this.formats = new ConfluenceMarkup();
    }

    @Override
    public void uri(String uri) {
    }

    @Override
    public void feature(Feature feature) {
        out.println(getFormat(HEADER1).text(feature.getName()));
        List<Tag> tags = feature.getTags();
        printTags(tags);
        String description = feature.getDescription().replaceAll("\\r\\n|\\r|\\n", " ");
        if (!description.isEmpty()) {
            out.println(description);
        }

    }

    @Override
    public void background(Background background) {
        replay();
        statement = background;
    }

    @Override
    public void scenario(Scenario scenario) {
        replay();
        statement = scenario;
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        replay();
        statement = scenarioOutline;
    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        // NoOp
    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        // NoOp
    }

    @Override
    public void examples(Examples examples) {
        replay();
        out.println();
        printComments(examples.getComments(), " ");
        printTags(examples.getTags());
        out.println(getFormat(TABLE_HEAD).text(getFormat(TABLE_HEAD_CELL).text(examples.getKeyword() + ": " + examples.getName())));
        out.println(getFormat(TABLE_ROW).text(getFormat(TABLE_CELL).text(
                getFormat(PANEL).text(renderTable(examples.getRows())))));
    }

    @Override
    public void step(Step step) {
        steps.add(step);
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void done() {
    }

    @Override
    public void close() {
        out.close();
    }

    public void eof() {
        replay();
    }

    private void replay() {
        printSectionTitle();
        printSteps();
    }

    private void printSteps() {
        if (steps.isEmpty()) return;
        while (!steps.isEmpty()) {
            printStep();
        }
    }

    private void printSectionTitle() {
        if (statement == null) return;

        out.println(
                formats.get(HEADER2).text(
                        statement.getName().isEmpty() ?
                                getFormat(COLOR_RED).text(getFormat(ITALICS).text("Undefined section")) :
                                statement.getName()
                ));

        if (statement instanceof TagStatement) {
            printTags(((TagStatement) statement).getTags());
        }
        out.println(statement.getDescription());
        statement = null;
    }

    private void printStep() {
        Step step = steps.remove(0);
        Format cell = getFormat(TABLE_CELL);
        Format row = getFormat(TABLE_ROW);
        Format bold = getFormat(BOLD);
        out.println(
                row.text(
                        cell.text(getFormat(COLOR_DARK_GREY).text(bold.text(step.getKeyword().trim()))) +
                                cell.text(step.getName().trim())));

        if (hasNestedTable(step)) {
            out.println(renderNestedTableWithinPanelInSecondColumn(step.getRows()));
        }
    }

    private String renderNestedTableWithinPanelInSecondColumn(List<DataTableRow> rows) {
        return getFormat(TABLE_ROW).text(
                getFormat(TABLE_CELL).text("") +
                getFormat(TABLE_CELL).text(getFormat(PANEL).text(renderTable(rows))));
    }

    private boolean hasNestedTable(Step step) {
        return step.getRows() != null;
    }

    private Format getFormat(ConfluenceMarkup.Formats key) {
        return formats.get(key);
    }

    private String renderTable(List<? extends Row> rows) {
        if (rows.isEmpty()) return "";

        StringBuilder table = new StringBuilder();

        for (int i = 0; i < rows.size(); i++) {
            Format rowFormat = isHeaderRow(i) ? getFormat(TABLE_HEAD) : getFormat(TABLE_ROW);
            Format cellFormat = isHeaderRow(i) ? getFormat(TABLE_HEAD_CELL) : getFormat(TABLE_CELL);

            StringBuilder cells = renderCells(rows.get(i), cellFormat);
            table.append(rowFormat.text(cells.toString())).append("\r\n");
        }

        return table.toString();
    }

    private StringBuilder renderCells(Row row, Format cellFormat) {
        StringBuilder cells = new StringBuilder();
        for (String cellContents : row.getCells()) {
            cells.append(cellFormat.text(cellContents));
        }
        return cells;
    }

    private boolean isHeaderRow(int i) {
        return (i == 0);
    }

    private void printComments(List<Comment> comments, String indent) {
        for (Comment comment : comments) {
            out.println(indent + comment.getValue());
        }
    }

    private void printTags(List<Tag> tags) {
        if (tags.isEmpty() || !options.isTagRenderingActive()) return;
        out.println(getFormat(INFO).text(
                " This section is tagged as " +
                        join(map(tags, tagNameMapper), ", ")));
    }

    public static class Options {
        private boolean tagRenderingActive;

        public Options(boolean TagRenderingActive) {
            this.tagRenderingActive = TagRenderingActive;
        }

        public boolean isTagRenderingActive() {
            return tagRenderingActive;
        }
    }
}