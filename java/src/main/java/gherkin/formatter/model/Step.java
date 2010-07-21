package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step extends BasicStatement {
    private Object multiline_arg;
    private Result result;

    public Step(List<Comment> comments, String keyword, String name, String description, int line, Object multilineArg, Result result) {
        super(comments, keyword, name, description, line);
        this.multiline_arg = multilineArg;
        this.result = result;
    }

    @Override
    public Map toMap() {
        Map map = super.toMap();
        if(multiline_arg instanceof List) {
            Map multilineArg = new HashMap();
            multilineArg.put("type", "table");
            multilineArg.put("value", map.get("multiline_arg"));
            map.put("multiline_arg", multilineArg);
        } else if(multiline_arg instanceof PyString) {
            ((Map)map.get("multiline_arg")).put("type", "py_string");
        }
        return map;
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.step(this);
    }

    public void setMultilineArg(Object multilineArg) {
        this.multiline_arg = multilineArg;
    }

    public Object getMultilineArg() {
        return multiline_arg;
    }

    public Result getResult() {
        return result;
    }
}
