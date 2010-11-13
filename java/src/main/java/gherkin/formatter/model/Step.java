package gherkin.formatter.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step extends BasicStatement {
    private Object multiline_arg;

    public Step(List<Comment> comments, String keyword, String name, int line) {
        super(comments, keyword, name, line);
    }

    @Override
    public Map<Object, Object> toMap() {
        Map<Object, Object> map = super.toMap();
        if (getRows() != null) {
            Map<Object, Object> multilineArg = new HashMap<Object, Object>();
            multilineArg.put("type", "table");
            multilineArg.put("value", map.get("multiline_arg"));
            map.put("multiline_arg", multilineArg);
        } else if (getPyString() != null) {
            ((Map<Object, Object>) map.get("multiline_arg")).put("type", "py_string");
        }
        return map;
    }

    @Override
    public Range getLineRange() {
        Range range = super.getLineRange();
        if (getRows() != null) {
            range = new Range(range.getFirst(), getRows().get(getRows().size() - 1).getLine());
        } else if (getPyString() != null) {
            range = new Range(range.getFirst(), getPyString().getLineRange().getLast());
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
        while(matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            result.add(new Argument(matchResult.start(), matchResult.group()));
        }
        return result;
    }

    public void setMultilineArg(Object multilineArg) {
        this.multiline_arg = multilineArg;
    }

    public Object getMultilineArg() {
        return multiline_arg;
    }

    public List<Row> getRows() {
        return multiline_arg instanceof List ? (List<Row>) multiline_arg : null;
    }

    public PyString getPyString() {
        return multiline_arg instanceof PyString ? (PyString) multiline_arg : null;
    }
}
