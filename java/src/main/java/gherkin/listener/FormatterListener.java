package gherkin.listener;

import gherkin.Listener;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;

import java.util.ArrayList;
import java.util.List;

public class FormatterListener implements Listener {
    private final Formatter formatter;
    private String uri;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Tag> tags = new ArrayList<Tag>();
    private Statement step;
    private List<Row> table;
    private Statement examples;
    private PyString pyString;

    public FormatterListener(Formatter formatter) {
        this.formatter = formatter;
    }

    public void location(String uri) {
        this.uri = uri;
    }

    public void comment(String comment, int line) {
        comments.add(new Comment(comment, line));
    }

    public void tag(String tag, int line) {
        tags.add(new Tag(tag, line));
    }

    public void feature(String keyword, String name, String description, int line) {
        formatter.feature(statement(grabComments(), grabTags(), keyword, name, description, line), uri);
    }

    public void background(String keyword, String name, String description, int line) {
        formatter.background(statement(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void scenario(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenario(statement(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenarioOutline(statement(grabComments(), grabTags(), keyword, name, description, line));
    }

    public void examples(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        examples = statement(grabComments(), grabTags(), keyword, name, description, line);
    }

    public void step(String keyword, String name, int line) {
        replayStepsOrExamples();
        step = statement(grabComments(), grabTags(), keyword, name, null, line);
    }

    public void row(List<String> cells, int line) {
        if (table == null) {
            table = new ArrayList<Row>();
        }
        table.add(new Row(grabComments(), cells, line));
    }

    public void pyString(String string, int line) {
        this.pyString = new PyString(string, line);
    }

    public void eof() {
        replayStepsOrExamples();
        formatter.eof();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        formatter.syntaxError(state, event, legalEvents, uri, line);
    }

    private Statement statement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        return new Statement(comments, tags, keyword, name, description, line);
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

    private List<Row> grabTable() {
        List<Row> table = this.table;
        this.table = null;
        return table;
    }

    private PyString grabPyString() {
        PyString pyString = this.pyString;
        this.pyString = null;
        return pyString;
    }

    private void replayStepsOrExamples() {
        if (step != null) {
            PyString pyString;
            List<Row> table;
            if ((pyString = grabPyString()) != null) {
                step.replayStep(formatter, pyString, null);
            } else if ((table = grabTable()) != null) {
                step.replayStep(formatter, table, null);
            } else {
                step.replayStep(formatter, (PyString) null, null);
            }
            step = null;
        }
        if (examples != null) {
            examples.replayExamples(formatter, grabTable());
            examples = null;
        }
    }
}
