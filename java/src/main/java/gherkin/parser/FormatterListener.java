package gherkin.parser;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;
import gherkin.lexer.Listener;

import java.util.ArrayList;
import java.util.List;

public class FormatterListener implements Listener {
    private final Formatter formatter;
    private Stash stash;
    private Builder currentBuilder;

    private class Stash {
        private List<Comment> comments;
        private List<Tag> tags;

        private String featureId;
        private String featureElementId;
        private String examplesId;
        private int rowIndex = 0;

        public void comment(Comment comment) {
            comments.add(comment);
        }

        public void tag(Tag tag) {
            tags.add(tag);
        }

        public void reset() {
            comments = new ArrayList<Comment>();
            tags = new ArrayList<Tag>();
        }

        public String featureId(String name) {
            return featureId = id(name);
        }

        public String featureElementId(String name) {
            return featureElementId = featureId  + ";" + id(name);
        }

        public String examplesId(String name) {
            rowIndex = 0;
            return examplesId = featureElementId  + ";" + id(name);
        }

        private String id(String name) {
            return name.replaceAll("[\\s_]", "-").toLowerCase();
        }

        public String nextExampleId() {
            rowIndex++;
            return "" + examplesId + ";" + rowIndex;
        }
    }

    public FormatterListener(Formatter formatter) {
        this.formatter = formatter;
        stash = new Stash();
        stash.reset();
    }

    public void comment(String comment, int line) {
        stash.comment(new Comment(comment, line));
    }

    public void tag(String tag, int line) {
        stash.tag(new Tag(tag, line));
    }

    public void feature(String keyword, String name, String description, int line) {
        formatter.feature(new Feature(stash.comments, stash.tags, keyword, name, description, line, stash.featureId(name)));
        stash.reset();
    }

    public void background(String keyword, String name, String description, int line) {
        formatter.background(new Background(stash.comments, keyword, name, description, line));
        stash.reset();
    }

    public void scenario(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenario(new Scenario(stash.comments, stash.tags, keyword, name, description, line, stash.featureElementId(name)));
        stash.reset();
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenarioOutline(new ScenarioOutline(stash.comments, stash.tags, keyword, name, description, line, stash.featureElementId(name)));
        stash.reset();
    }

    public void examples(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        currentBuilder = new Examples.Builder(stash.comments, stash.tags, keyword, name, description, line, stash.examplesId(name));
        stash.reset();
    }

    public void step(String keyword, String name, int line) {
        replayStepsOrExamples();
        currentBuilder = new Step.Builder(stash.comments, keyword, name, line);
        stash.reset();
    }

    public void row(List<String> cells, int line) {
        currentBuilder.row(stash.comments, cells, line, stash.nextExampleId());
        stash.reset();
    }

    public void docString(String contentType, String content, int line) {
        currentBuilder.docString(new DocString(contentType, content, line));
    }

    public void eof() {
        replayStepsOrExamples();
        formatter.eof();
    }

    /**
     * Not part of the API. Used for testing only.
     */
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        formatter.syntaxError(state, event, legalEvents, uri, line);
    }

    private void replayStepsOrExamples() {
        if (currentBuilder != null) {
            currentBuilder.replay(formatter);
            currentBuilder = null;
        }
    }

}
