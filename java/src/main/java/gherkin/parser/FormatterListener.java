package gherkin.parser;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;
import gherkin.lexer.Listener;

import java.util.ArrayList;
import java.util.List;

public class FormatterListener implements Listener {
    private final Formatter formatter;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Tag> tags = new ArrayList<Tag>();
    private Step step;
    private List<Row> table;
    private Examples examples;
    private DocString docString;

    public FormatterListener(Formatter formatter) {
        this.formatter = formatter;
    }

    public void comment(String comment, int line) {
        comments.add(new Comment(comment, line));
    }

    public void tag(String tag, int line) {
        tags.add(new Tag(tag, line));
    }

    public void feature(String keyword, String name, String description, int line) {
        formatter.feature(new Feature(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void background(String keyword, String name, String description, int line) {
        formatter.background(new Background(grabComments(), keyword, name, description, line));
    }

    public void scenario(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenario(new Scenario(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenarioOutline(new ScenarioOutline(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void examples(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        examples = new Examples(grabComments(), grabTags(), keyword, name, description, line, null);
    }

    public void step(String keyword, String name, int line) {
        replayStepsOrExamples();
        step = new Step(grabComments(), keyword, name, line);
    }

    public void row(List<String> cells, int line) {
        if (table == null) {
            table = new ArrayList<Row>();
        }
        table.add(new Row(grabComments(), cells, line));
    }

    public void docString(String string, int line) {
        this.docString = new DocString(string, line);
    }

    public void eof() {
        replayStepsOrExamples();
        formatter.eof();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, int line) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not part of the API. Used for testing only.
     */
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        formatter.syntaxError(state, event, legalEvents, uri, line);
    }

    private List<Comment> grabComments() {
        List<Comment> comments = this.comments;
        this.comments = new ArrayList<Comment>();
        return comments;
    }

    private List<Tag> grabTags() {
        List<Tag> tags = this.tags;
        this.tags = new ArrayList<Tag>();
        return tags;
    }

    private List<Row> grabRows() {
        List<Row> table = this.table;
        this.table = null;
        return table;
    }

    private DocString grabDocString() {
        DocString docString = this.docString;
        this.docString = null;
        return docString;
    }

    private void replayStepsOrExamples() {
        if (step != null) {
            DocString docString;
            List<Row> rows;
            if ((docString = grabDocString()) != null) {
                step.setMultilineArg(docString);
            } else if ((rows = grabRows()) != null) {
                step.setMultilineArg(rows);
            }
            step.replay(formatter);
            step = null;
        }
        if (examples != null) {
            examples.setRows(grabRows());
            examples.replay(formatter);
            examples = null;
        }
    }
}
