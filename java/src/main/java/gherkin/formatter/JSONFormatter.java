package gherkin.formatter;

import gherkin.formatter.model.*;
import net.iharder.Base64;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFormatter implements Reporter, Formatter {
    private final List<Map<Object, Object>> featureMaps = new ArrayList<Map<Object, Object>>();
    private final NiceAppendable out;

    private Map<Object, Object> featureMap;
    private int stepIndex = 0;
    private String uri;

    public JSONFormatter(Appendable out) {
        this.out = new NiceAppendable(out);
    }

    @Override
    public void close() {
        out.append(JSONValue.toJSONString(featureMaps));
    }

    @Override
    public void uri(String uri) {
        this.uri = uri;
    }

    @Override
    public void feature(Feature feature) {
        featureMap = feature.toMap();
        featureMap.put("uri", uri);
        featureMaps.add(featureMap);
    }

    @Override
    public void background(Background background) {
        getFeatureElements().add(background.toMap());
        stepIndex = 0;
    }

    @Override
    public void scenario(Scenario scenario) {
        getFeatureElements().add(scenario.toMap());
        stepIndex = 0;
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        getFeatureElements().add(scenarioOutline.toMap());
        stepIndex = 0;
    }

    @Override
    public void examples(Examples examples) {
        getAllExamples().add(examples.toMap());
    }

    @Override
    public void step(Step step) {
        getSteps().add(step.toMap());
    }

    @Override
    public void match(Match match) {
        getStepAt(stepIndex).put("match", match.toMap());
    }

    @Override
    public void result(Result result) {
        getStepAt(stepIndex).put("result", result.toMap());
        stepIndex++;
    }

    @Override
    public void embedding(final String mimeType, final byte[] data) {
        final Map<String, String> embedding = new HashMap<String, String>() {{
            put("mime_type", mimeType);
            put("data", Base64.encodeBytes(data));
        }};
        getEmbeddings().add(embedding);
    }

    @Override
    public void eof() {
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException();
    }

    private List<Object> getFeatureElements() {
        List<Object> featureElements = (List) featureMap.get("elements");
        if (featureElements == null) {
            featureElements = new ArrayList<Object>();
            featureMap.put("elements", featureElements);
        }
        return featureElements;
    }

    private Map<Object, List<Object>> getFeatureElement() {
        return (Map) getFeatureElements().get(getFeatureElements().size() - 1);
    }

    private List<Object> getAllExamples() {
        List<Object> allExamples = getFeatureElement().get("examples");
        if (allExamples == null) {
            allExamples = new ArrayList<Object>();
            getFeatureElement().put("examples", allExamples);
        }
        return allExamples;
    }

    private List<Object> getSteps() {
        List<Object> steps = getFeatureElement().get("steps");
        if (steps == null) {
            steps = new ArrayList<Object>();
            getFeatureElement().put("steps", steps);
        }
        return steps;
    }

    private Map<Object, Object> getLastStep() {
        return getStepAt(getSteps().size() - 1);
    }

    private Map<Object, Object> getStepAt(int index) {
        return (Map) getSteps().get(index);
    }

    private List getEmbeddings() {
        List<Object> embeddings = (List<Object>) getLastStep().get("embeddings");
        if (embeddings == null) {
            embeddings = new ArrayList<Object>();
            getLastStep().put("embeddings", embeddings);
        }
        return embeddings;
    }
}
