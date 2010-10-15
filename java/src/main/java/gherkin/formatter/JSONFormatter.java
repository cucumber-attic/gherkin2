package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONFormatter implements Formatter {
    private final Writer out;
    private Map featureHash;

    public JSONFormatter(Writer out) {
        this.out = out;
    }

    public JSONFormatter(OutputStream out) throws UnsupportedEncodingException {
        this(new OutputStreamWriter(out, "UTF-8"));
    }

    public void uri(String uri) {
    }

    public void feature(Feature feature) {
        featureHash = feature.toMap();
    }

    public void background(Background background) {
        getFeatureElements().add(background.toMap());
    }

    public void scenario(Scenario scenario) {
        getFeatureElements().add(scenario.toMap());
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        getFeatureElements().add(scenarioOutline.toMap());
    }

    public void examples(Examples examples) {
        getAllExamples().add(examples.toMap());
    }

    public void step(Step step) {
        getSteps().add(step.toMap());
    }

    public void eof() {
        try {
            out.write(JSONValue.toJSONString(featureHash));
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

    public void steps(List<Step> steps) {
    }

    private List getFeatureElements() {
        List featureElements = (List) featureHash.get("elements");
        if (featureElements == null) {
            featureElements = new ArrayList();
            featureHash.put("elements", featureElements);
        }
        return featureElements;
    }

    private Map getFeatureElement() {
        return (Map) getFeatureElements().get(getFeatureElements().size() - 1);
    }

    private List getAllExamples() {
        List allExamples = (List) getFeatureElement().get("examples");
        if (allExamples == null) {
            allExamples = new ArrayList();
            getFeatureElement().put("examples", allExamples);
        }
        return allExamples;
    }

    private List getSteps() {
        List steps = (List) getFeatureElement().get("steps");
        if (steps == null) {
            steps = new ArrayList();
            getFeatureElement().put("steps", steps);
        }
        return steps;
    }
}
