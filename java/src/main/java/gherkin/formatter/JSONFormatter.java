package gherkin.formatter;

import gherkin.formatter.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.List;

public class JSONFormatter implements Formatter {
    private final Writer out;
    private JSONObject featureHash;

    public JSONFormatter(Writer out) {
        this.out = out;
    }

    public JSONFormatter(OutputStream out) throws UnsupportedEncodingException {
        this(new OutputStreamWriter(out, "UTF-8"));
    }

    public void feature(Statement statement, String uri) {
        featureHash = statementHash("feature", statement);
        featureHash.put("uri", uri);
    }

    public void background(Statement statement) {
        addStepContainer("background", statement);
    }

    public void scenario(Statement statement) {
        addStepContainer("scenario", statement);
    }

    public void scenarioOutline(Statement statement) {
        addStepContainer("scenario_outline", statement);
    }

    public void examples(Statement statement, List<Row> exampleRows) {
        JSONObject tableContainer = addExamples(statement);
        tableContainer.put("table", toHashArray(exampleRows));
    }

    public void step(Statement statement, List<Row> stepTable, Result result) {
        JSONObject step = addStep(statement);
        if(stepTable != null) {
            JSONObject multilineArg = new JSONObject();
            multilineArg.put("type", "table");
            multilineArg.put("value", toHashArray(stepTable));
            step.put("multiline_arg", multilineArg);
        }
    }

    public void step(Statement statement, PyString pyString, Result result) {
        JSONObject step = addStep(statement);
        if(pyString != null) {
            JSONObject multilineArg = new JSONObject();
            multilineArg.put("type", "py_string");
            multilineArg.put("value", pyString.getValue());
            multilineArg.put("line", pyString.getLine());
            step.put("multiline_arg", multilineArg);
        }
    }

    private JSONObject addStep(Statement statement) {
        JSONObject step = statementHash(null, statement);
        JSONArray steps = (JSONArray) lastElement().get("steps");
        if(steps == null) {
            steps = new JSONArray();
            lastElement().put("steps", steps);
        }
        steps.add(step);
        return step;
    }


    public void eof() {
        try {
            out.write(featureHash.toJSONString());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to " + out, e);
        }
    }

    public void table(List<Row> rows) {
        throw new UnsupportedOperationException();
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException();
    }

    private JSONObject statementHash(String type, Statement statement) {
        JSONObject element = new JSONObject();
        element.put("keyword", statement.getKeyword());
        element.put("name", statement.getName());
        element.put("line", statement.getLine());
        if(type != null) {
            element.put("type", type);
        }
        addComments(element, statement);
        addTags(element, statement);
        if(statement.getDescription() != null) {
            element.put("description", statement.getDescription());
        }
        return element;
    }

    private void addComments(JSONObject element, CommentHolder commentHolder) {
        if(commentHolder.getComments() != null && !commentHolder.getComments().isEmpty()) {
            JSONArray comments = new JSONArray();
            element.put("comments", comments);
            for(Comment comment: commentHolder.getComments()) {
                JSONObject c = new JSONObject();
                comments.add(c);
                c.put("value", comment.getValue());
                c.put("line", comment.getLine());
            }
        }
    }

    private void addTags(JSONObject element, Statement statement) {
        if(statement.getTags() != null && !statement.getTags().isEmpty()) {
            JSONArray tags = new JSONArray();
            element.put("tags", tags);
            for(Tag tag: statement.getTags()) {
                JSONObject t = new JSONObject();
                tags.add(t);
                t.put("name", tag.getName());
                t.put("line", tag.getLine());
            }
        }
    }

    private JSONObject addExamples(Statement statement) {
        JSONObject element = statementHash("examples", statement);
        JSONArray examples = (JSONArray) lastElement().get("examples");
        if(examples == null) {
            examples = new JSONArray();
            lastElement().put("examples", examples);
        }
        examples.add(element);
        return element;
    }

    private void addStepContainer(String type, Statement statement) {
        addElement(type, statement);
        lastElement().put("steps", new JSONArray());
    }

    private void addElement(String type, Statement statement) {
        JSONObject element = statementHash(type, statement);
        elements().add(element);
    }

    private JSONArray elements() {
        JSONArray elements = (JSONArray) featureHash.get("elements");
        if(elements == null) {
            elements = new JSONArray();
            featureHash.put("elements", elements);
        }
         return elements;
    }

    private JSONObject lastElement() {
        return (JSONObject) elements().get(elements().size() - 1);
    }

    private JSONArray toHashArray(List<Row> rows) {
        JSONArray result = new JSONArray();
        for(Row row: rows) {
            JSONObject e = new JSONObject();
            e.put("cells", row.getCells());
            e.put("line", row.getLine());
            addComments(e, row);
            result.add(e);
        }
        return result;
    }

}
