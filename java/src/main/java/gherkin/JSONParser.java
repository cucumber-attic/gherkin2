package gherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSONParser implements FeatureParser {
    private final Formatter formatter;

    public JSONParser(Formatter formatter) {
        this.formatter = formatter;
    }

    public void parse(String src, String uri, int offset) {
        formatter.uri(uri);
        JSONObject o = (JSONObject) JSONValue.parse(src);
        new Feature(comments(o), tags(o), keyword(o), name(o), description(o), line(o)).replay(formatter);
        for (Object e : getList(o, "elements")) {
            JSONObject featureElement = (JSONObject) e;
            featureElement(featureElement).replay(formatter);
            for (Object s : getList(featureElement, "steps")) {
                JSONObject step = (JSONObject) s;
                step(step).replay(formatter);
            }
            for (Object s : getList(featureElement, "examples")) {
                JSONObject eo = (JSONObject) s;
                new Examples(comments(eo), tags(eo), keyword(eo), name(eo), description(eo), line(eo), rows(getList(eo, "rows"))).replay(formatter);
            }
        }
        formatter.eof();
    }

    private BasicStatement featureElement(JSONObject o) {
        String type = (String) o.get("type");
        if (type.equals("background")) {
            return new Background(comments(o), keyword(o), name(o), description(o), line(o));
        } else if (type.equals("scenario")) {
            return new Scenario(comments(o), tags(o), keyword(o), name(o), description(o), line(o));
        } else if (type.equals("scenario_outline")) {
            return new ScenarioOutline(comments(o), tags(o), keyword(o), name(o), description(o), line(o));
        } else {
            return null;
        }
    }

    private Step step(JSONObject o) {
        Object multilineArg = null;
        if (o.containsKey("multiline_arg")) {
            Map ma = (Map) o.get("multiline_arg");
            if (ma.get("type").equals("table")) {
                multilineArg = rows(getList(ma, "value"));
            } else {
                multilineArg = new PyString(getString(ma, "value"), getInt(ma, "line"));
            }
        }
        return new Step(comments(o), keyword(o), name(o), line(o), multilineArg, null);
    }

    private List<Row> rows(List o) {
        List<Row> rows = new ArrayList<Row>(o.size());
        for (Object e : o) {
            Map row = (Map) e;
            rows.add(new Row(comments(row), getList(row, "cells"), getInt(row, "line")));
        }
        return rows;
    }

    private List<Comment> comments(Map o) {
        List<Comment> comments = new ArrayList<Comment>();
        if (o.containsKey("comments")) {
            for (Object e : ((List) o.get("comments"))) {
                Map map = (Map) e;
                comments.add(new Comment(getString(map, "value"), getInt(map, "line")));
            }
        }
        return comments;
    }

    private List<Tag> tags(Map o) {
        List<Tag> tags = new ArrayList<Tag>();
        if (o.containsKey("tags")) {
            for (Object e : ((List) o.get("tags"))) {
                Map map = (Map) e;
                tags.add(new Tag(getString(map, "name"), getInt(map, "line")));
            }
        }
        return tags;
    }

    private String keyword(Map o) {
        return getString(o, "keyword");
    }

    private String name(Map o) {
        return getString(o, "name");
    }

    private String description(Map o) {
        return getString(o, "description");
    }

    private int line(Map o) {
        return getInt(o, "line");
    }

    private String getString(Map map, String key) {
        Object string = map.get(key);
        return string == null ? null : (String) string;
    }

    private int getInt(Map map, String key) {
        Object n = map.get(key);
        return n == null ? -1 : ((Number) n).intValue();
    }

    private List getList(Map map, String key) {
        Object list = map.get(key);
        return list == null ? Collections.emptyList() : (List) list;
    }
}
