package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public class Statement implements CommentHolder {
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final String keyword;
    private final String name;
    private final String description;
    private final int line;

    public Statement(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        this.comments = comments;
        this.tags = tags;
        this.keyword = keyword;
        this.name = name;
        this.description = description;
        this.line = line;
    }

    public Range getLineRange() {
        int first;
        if(getComments().size() > 0) {
            first = getComments().get(0).getLine();
        } else if(getTags().size() > 0) {
            first = getTags().get(0).getLine();
        } else {
            first = getLine();
        }

        return new Range(first, getLine());
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLine() {
        return line;
    }

    public void replayStep(Formatter formatter, PyString pyString, Result result) {
        formatter.step(this, pyString, result);
    }

    public void replayStep(Formatter formatter, List<Row> table, Result result) {
        formatter.step(this, table, result);
    }

    public void replayExamples(Formatter formatter, List<Row> examplesRows) {
        formatter.examples(this, examplesRows);
    }

    public void replayBackground(Formatter formatter) {
        formatter.background(this);
    }

    public void replayFeature(Formatter formatter, String uri) {
        formatter.feature(this, uri);
    }

    public void replayScenarioOutline(Formatter formatter) {
        formatter.scenarioOutline(this);
    }

    public void replayScenario(Formatter formatter) {
        formatter.scenario(this);
    }
}
