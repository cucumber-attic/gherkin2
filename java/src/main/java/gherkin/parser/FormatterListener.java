package gherkin.parser;

import gherkin.Listener;
import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class FormatterListener implements Listener {
    private final Formatter formatter;
    private String uri;
    private int offset;
    private List<String> comments = new ArrayList<String>();
    private List<String> tags = new ArrayList<String>();
    private Step step;
    private List<Row> table;
    private Examples examples;
    private String pyString;

    public FormatterListener(Formatter formatter) {
        this.formatter = formatter;
    }

    public void location(String uri, int offset) {
        this.uri = uri;
        this.offset = offset;
    }

    public void comment(String comment, int line) {
        comments.add(comment);
    }

    public void tag(String tag, int line) {
        tags.add(tag);
    }

    public void feature(String keyword, String name, String description, int line) {
        formatter.feature(grabComments(), grabTags(), keyword, name, description, uri);
    }

    public void background(String keyword, String name, String description, int line) {
        formatter.background(grabComments(), keyword, name, description, line+offset);
    }

    public void scenario(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenario(grabComments(), grabTags(), keyword, name, description, line+offset);
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        formatter.scenarioOutline(grabComments(), grabTags(), keyword, name, description, line+offset);
    }

    public void examples(String keyword, String name, String description, int line) {
        replayStepsOrExamples();
        examples = new Examples(grabComments(), grabTags(), keyword, name, description, line+offset);
    }

    public void step(String keyword, String name, int line) {
        replayStepsOrExamples();
        step = new Step(grabComments(), keyword, name, line+offset);
    }

    public void row(List<String> cells, int line) {
        if(table == null) {
            table = new ArrayList<Row>();
        }
        table.add(new Row(cells, grabComments(), line));
    }

    public void pyString(String string, int line) {
        this.pyString = string;
    }

    public void eof() {
        replayStepsOrExamples();
        formatter.eof();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, int line) {
        throw new UnsupportedOperationException();
    }

    private List<String> grabComments() {
        List<String> comments = this.comments;
        this.comments = new ArrayList<String>();
        return comments;
    }

    private List<String> grabTags() {
        List<String> tags = this.tags;
        this.tags = new ArrayList<String>();
        return tags;
    }

    private List<Row> grabTable() {
        List<Row> table = this.table;
        this.table = null;
        return table;
    }

    private String grabPyString() {
        String pyString = this.pyString;
        this.pyString = null;
        return pyString;
    }

    private void replayStepsOrExamples() {
        if(step != null) {
            String pyString;
            List<Row> table;
            if((pyString = grabPyString()) != null) {
                step.replay(formatter, pyString);
            } else if((table = grabTable()) != null) {
                step.replay(formatter, table);
            } else {
                step.replay(formatter, (String) null);
            }
            step = null;
        }
        if(examples != null) {
            examples.replay(formatter, grabTable());
        }
    }

    private class Step {
        private final List<String> comments;
        private final String keyword;
        private final String name;
        private final int line;

        public Step(List<String> comments, String keyword, String name, int line) {
            this.comments = comments;
            this.keyword = keyword;
            this.name = name;
            this.line = line;
        }

        public void replay(Formatter formatter, String pyString) {
            formatter.step(comments, keyword, name, line, pyString, null, null, null, null);
        }

        public void replay(Formatter formatter, List<Row> table) {
            formatter.step(comments, keyword, name, line, table, null, null, null, null);
        }
    }


    private class Examples {
        private final List<String> comments;
        private final List<String> tags;
        private final String keyword;
        private final String name;
        private final String description;
        private final int line;

        public Examples(List<String> comments, List<String> tags, String keyword, String name, String description, int line) {
            this.comments = comments;
            this.tags = tags;
            this.keyword = keyword;
            this.name = name;
            this.description = description;
            this.line = line;
        }

        public void replay(Formatter formatter, List<Row> examplesTable) {
            formatter.examples(comments, tags, keyword, name, description, line, examplesTable);
        }
    }
}
