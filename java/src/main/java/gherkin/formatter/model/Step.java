package gherkin.formatter.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step extends BasicStatement {
    private static final long serialVersionUID = 1L;

    private final List<DataTableRow> rows;
    private final DocString doc_string;

    public static class Builder implements gherkin.formatter.model.Builder {
        private final List<Comment> comments;
        private final String keyword;
        private final String name;
        private final Integer line;
        private List<DataTableRow> rows;
        private DocString doc_string;

        public Builder(List<Comment> comments, String keyword, String name, Integer line) {
            this.comments = comments;
            this.keyword = keyword;
            this.name = name;
            this.line = line;
        }

        public void row(List<Comment> comments, List<String> cells, Integer line, String id) {
            if (rows == null) {
                rows = new ArrayList<DataTableRow>();
            }
            rows.add(new DataTableRow(comments, cells, line));
        }

        public void replay(Formatter formatter) {
            new Step(comments, keyword, name, line, rows, doc_string).replay(formatter);
        }

        public void docString(DocString docString) {
            doc_string = docString;
        }
    }

    public Step(List<Comment> comments, String keyword, String name, Integer line, List<DataTableRow> rows, DocString docString) {
        super(comments, keyword, name, line);
        this.rows = rows;
        this.doc_string = docString;
    }

    @Override
    public Range getLineRange() {
        Range range = super.getLineRange();
        if (getRows() != null) {
            range = new Range(range.getFirst(), getRows().get(getRows().size() - 1).getLine());
        } else if (getDocString() != null) {
            range = new Range(range.getFirst(), getDocString().getLineRange().getLast());
        }
        return range;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.step(this);
    }

    public List<Argument> getOutlineArgs() {
        List<Argument> result = new ArrayList<Argument>();
        Pattern p = Pattern.compile("<[^<]*>");
        Matcher matcher = p.matcher(getName());
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            result.add(new Argument(matchResult.start(), matchResult.group()));
        }
        return result;
    }

    public Match getOutlineMatch(String location) {
        return new Match(getOutlineArgs(), location);
    }

    public List<DataTableRow> getRows() {
        return rows;
    }

    public DocString getDocString() {
        return doc_string;
    }

    public StackTraceElement getStackTraceElement(String path) {
        return new StackTraceElement("✽", getKeyword() + getName(), path, getLine());
    }
}
