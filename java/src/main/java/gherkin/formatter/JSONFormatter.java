package gherkin.formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import net.iharder.Base64;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFormatter implements Formatter, Reporter {
    private final List<Map<String, Object>> featureMaps = new ArrayList<Map<String, Object>>();
    private final NiceAppendable out;

    private Map<String, Object> featureMap;
    private String uri;
    private Map<String, Object> background;
    private List<Map<String, Object>> steps = new ArrayList<Map<String, Object>>();

    public JSONFormatter(Appendable out) {
        this.out = new NiceAppendable(out);
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
        background = null;
    }

    @Override
    public void background(Background background) {
        this.background = background.toMap();
        getFeatureElements().add(this.background);
    }

    @Override
    public void scenario(Scenario scenario) {
        this.background = null;
        getFeatureElements().add(scenario.toMap());
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        this.background = null;
        getFeatureElements().add(scenarioOutline.toMap());
    }

    @Override
    public void examples(Examples examples) {
        getAllExamples().add(examples.toMap());
    }

    @Override
    public void step(Step step) {
        Map<String, Object> stepMap = step.toMap();
        steps.add(stepMap);
        getSteps().add(stepMap);
    }

    @Override
    public void eof() {
    }

    @Override
    public void done() {
        out.append(gson().toJson(featureMaps));
        // We're *not* closing the stream here.
        // https://github.com/cucumber/gherkin/issues/151
        // https://github.com/cucumber/cucumber-jvm/issues/96
    }

    @Override
    public void close() {
        out.close();
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void match(Match match) {
        steps.get(0).put("match", match.toMap());
    }

    @Override
    public void result(Result result) {
        steps.remove(0).put("result", result.toMap());
    }

    @Override
    public void hook(String type, Match match, Result result) {
        List<Map<String, Object>> hooks = (List<Map<String, Object>>) getFeatureElement().get("hooks");
        if (hooks == null) {
            hooks = new ArrayList<Map<String, Object>>();
            getFeatureElement().put("hooks", hooks);
        }
        Map hookMap = new HashMap();
        hookMap.put("type", type);
        hookMap.put("match", match.toMap());
        hookMap.put("result", result.toMap());
        hooks.add(hookMap);
    }

    @Override
    public void embedding(String mimeType, byte[] data) {
        final Map<String, String> embedding = new HashMap<String, String>();
        embedding.put("mime_type", mimeType);
        embedding.put("data", Base64.encodeBytes(data));
        getEmbeddings().add(embedding);
    }

    private List<Map<String, String>> getEmbeddings() {
        List<Map<String, String>> embeddings = (List<Map<String, String>>) steps.get(0).get("embeddings");
        if (embeddings == null) {
            embeddings = new ArrayList<Map<String, String>>();
            steps.get(0).put("embeddings", embeddings);
        }
        return embeddings;
    }

    @Override
    public void write(String text) {
        getOutput().add(text);
    }

    private List<String> getOutput() {
        List<String> output = (List<String>) steps.get(0).get("output");
        if (output == null) {
            output = new ArrayList<String>();
            steps.get(0).put("output", output);
        }
        return output;
    }

    private List<Map<String, Object>> getFeatureElements() {
        List<Map<String, Object>> featureElements = (List) featureMap.get("elements");
        if (featureElements == null) {
            featureElements = new ArrayList<Map<String, Object>>();
            featureMap.put("elements", featureElements);
        }
        return featureElements;
    }

    private Map<String, Object> getFeatureElement() {
        if (background != null) {
            return background;
        } else {
            return (Map) getFeatureElements().get(getFeatureElements().size() - 1);
        }
    }

    private List<Map<String, Object>> getAllExamples() {
        List<Map<String, Object>> allExamples = (List<Map<String, Object>>) getFeatureElement().get("examples");
        if (allExamples == null) {
            allExamples = new ArrayList<Map<String, Object>>();
            getFeatureElement().put("examples", allExamples);
        }
        return allExamples;
    }

    private List<Map<String, Object>> getSteps() {
        List<Map<String, Object>> steps = (List<Map<String, Object>>) getFeatureElement().get("steps");
        if (steps == null) {
            steps = new ArrayList<Map<String, Object>>();
            getFeatureElement().put("steps", steps);
        }
        return steps;
    }

    protected Gson gson() {
        return new GsonBuilder().create();
    }
}
