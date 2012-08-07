package gherkin;

import com.google.gson.Gson;
import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.BasicStatement;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DataTableRow;
import gherkin.formatter.model.DocString;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import net.iharder.Base64;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private final Gson gson = new Gson();
    private final Reporter reporter;
    private final Formatter formatter;

    public JSONParser(Reporter reporter, Formatter formatter) {
        this.reporter = reporter;
        this.formatter = formatter;
    }

    public void parse(String src) {
        List<Map> featureHashes = gson.fromJson(new StringReader(src), List.class);
        for (Map o : featureHashes) {
            formatter.uri(getString(o, "uri"));
            new Feature(comments(o), tags(o), keyword(o), name(o), description(o), line(o), id(o)).replay(formatter);
            for (Map featureElement : (List<Map>) getList(o, "elements")) {
                featureElement(featureElement).replay(formatter);
                List<Map> hooks = (List<Map>) getList(featureElement, "hooks");

                for (Map hook : hooks) {
                    if("before".equals(hook.get("type"))) {
                        hook(hook);
                    }
                }
                for (Map hook : hooks) {
                    if("background".equals(hook.get("type"))) {
                        hook(hook);
                    }
                }
                for (Map step : (List<Map>) getList(featureElement, "steps")) {
                    step(step);
                }
                for (Map hook : hooks) {
                    if("after".equals(hook.get("type"))) {
                        hook(hook);
                    }
                }
                for (Map eo : (List<Map>) getList(featureElement, "examples")) {
                    new Examples(comments(eo), tags(eo), keyword(eo), name(eo), description(eo), line(eo), id(eo), examplesTableRows(getList(eo, "rows"))).replay(formatter);
                }
            }
            formatter.eof();
        }
    }

    private BasicStatement featureElement(Map o) {
        String type = (String) o.get("type");
        if (type.equals("background")) {
            return new Background(comments(o), keyword(o), name(o), description(o), line(o));
        } else if (type.equals("scenario")) {
            return new Scenario(comments(o), tags(o), keyword(o), name(o), description(o), line(o), id(o));
        } else if (type.equals("scenario_outline")) {
            return new ScenarioOutline(comments(o), tags(o), keyword(o), name(o), description(o), line(o), id(o));
        } else {
            return null;
        }
    }

    private void hook(Map o) {
        String type = (String) o.get("type");
        Map m = (Map) o.get("match");
        Match match = new Match(arguments(m), location(m));
        Map r = (Map) o.get("result");
        Result result = new Result(status(r), duration(r), errorMessage(r));
        reporter.hook(type, match, result);
    }

    private void step(Map o) {
        List<DataTableRow> rows = null;
        if (o.containsKey("rows")) {
            rows = dataTableRows(getList(o, "rows"));
        }

        DocString docString = null;
        if (o.containsKey("doc_string")) {
            Map ds = (Map) o.get("doc_string");
            docString = new DocString(getString(ds, "content_type"), getString(ds, "value"), getInt(ds, "line"));
        }

        Step step = new Step(comments(o), keyword(o), name(o), line(o), rows, docString);
        step.replay(formatter);

        if (o.containsKey("match")) {
            Map m = (Map) o.get("match");
            new Match(arguments(m), location(m)).replay(reporter);
        }

        if (o.containsKey("result")) {
            Map r = (Map) o.get("result");
            new Result(status(r), duration(r), errorMessage(r)).replay(reporter);
        }

        if (o.containsKey("embeddings")) {
            List<Map> embeddings = (List<Map>) o.get("embeddings");
            for (Map embedding : embeddings) {
                try {
                    reporter.embedding(getString(embedding, "mime_type"), Base64.decode(getString(embedding, "data")));
                } catch (IOException ex) {
                    throw new RuntimeException("Couldn't decode data", ex);
                }
            }
        }

        if (o.containsKey("output")) {
            List<String> output = (List<String>) o.get("output");
            for (String text : output) {
                reporter.write(text);
            }
        }
    }

    private List<DataTableRow> dataTableRows(List o) {
        List<DataTableRow> rows = new ArrayList<DataTableRow>(o.size());
        for (Object e : o) {
            Map row = (Map) e;
            rows.add(new DataTableRow(comments(row), getList(row, "cells"), getInt(row, "line")));
        }
        return rows;
    }

    private List<ExamplesTableRow> examplesTableRows(List o) {
        List<ExamplesTableRow> rows = new ArrayList<ExamplesTableRow>(o.size());
        for (Object e : o) {
            Map row = (Map) e;
            rows.add(new ExamplesTableRow(comments(row), getList(row, "cells"), getInt(row, "line"), id(row)));
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

    private Integer line(Map o) {
        return getInt(o, "line");
    }

    private String id(Map o) {
        return getString(o, "id");
    }

    private List<Argument> arguments(Map m) {
        List arguments = getList(m, "arguments");
        List<Argument> result = new ArrayList<Argument>();
        for (Object argument : arguments) {
            Map argMap = (Map) argument;
            result.add(new Argument(getInt(argMap, "offset"), getString(argMap, "val")));
        }
        return result;
    }

    private String location(Map m) {
        return getString(m, "location");
    }

    private String status(Map r) {
        return getString(r, "status");
    }

    private long duration(Map r) {
        return getLong(r, "duration");
    }

    private String errorMessage(Map r) {
        return getString(r, "error_message");
    }

    private String getString(Map map, String key) {
        Object string = map.get(key);
        return string == null ? null : (String) string;
    }

    private Integer getInt(Map map, String key) {
        Object n = map.get(key);
        return n == null ? null : ((Number) n).intValue();
    }

    private Long getLong(Map map, String key) {
        Object n = map.get(key);
        return n == null ? null : ((Number) n).longValue();
    }

    private List getList(Map map, String key) {
        Object list = map.get(key);
        return list == null ? Collections.emptyList() : (List) list;
    }
}
