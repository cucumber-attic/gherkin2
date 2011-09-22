package gherkin.formatter.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step extends BasicStatement {
    private List<Row> rows;
    private DocString doc_string;

    public Step(List<Comment> comments, String keyword, String name, int line) {
        super(comments, keyword, name, line);
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

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setDocString(DocString docString) {
        this.doc_string = docString;
    }

    public DocString getDocString() {
        return doc_string;
    }

    public StackTraceElement getStackTraceElement(String path) {
        return new StackTraceElement("✽", getKeyword() + getName(), path, getLine());
    }
}
