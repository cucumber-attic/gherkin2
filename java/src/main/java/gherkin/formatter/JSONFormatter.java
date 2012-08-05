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

public class JSONFormatter implements Reporter, Formatter {
    private final List<Map<String, Object>> featureMaps = new ArrayList<Map<String, Object>>();
    private final NiceAppendable out;

    private Map<String, Object> featureMap;
    private String uri;
    private Map<String, Object> currentStep;
    private int stepIndex = 0;
    private Map<String, Object> background;

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
        getSteps().add(step.toMap());
    }

    @Override
    public void match(Match match) {
        if (background != null) {
            throw new IllegalStateException("Can't match a background step. This is a bug in library using gherkin.");
        }
        currentStep = nextStep();
        currentStep.put("match", match.toMap());
    }

    private Map<String, Object> nextStep() {
        return getSteps().get(stepIndex++);
    }

    @Override
    public void result(Result result) {
        if (background != null) {
            throw new IllegalStateException("Can't add result to a background step. This is a bug in library using gherkin.");
        }
        currentStep.put("result", result.toMap());
    }

    @Override
    public void before(Match match, Result result) {
        addHook(match, result, "before");
    }

    @Override
    public void after(Match match, Result result) {
        addHook(match, result, "after");
    }

    private void addHook(final Match match, final Result result, String hook) {
        List<Map<String, Object>> hooks = (List<Map<String, Object>>) getFeatureElement().get(hook);
        if (hooks == null) {
            hooks = new ArrayList<Map<String, Object>>();
            getFeatureElement().put(hook, hooks);
        }
        Map hookMap = new HashMap();
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

    @Override
    public void write(String text) {
        getOutput().add(text);
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

    private List<Map<String, String>> getEmbeddings() {
        List<Map<String, String>> embeddings = (List<Map<String, String>>) currentStep.get("embeddings");
        if (embeddings == null) {
            embeddings = new ArrayList<Map<String, String>>();
            currentStep.put("embeddings", embeddings);
        }
        return embeddings;
    }

    private List<String> getOutput() {
        List<String> output = (List<String>) currentStep.get("output");
        if (output == null) {
            output = new ArrayList<String>();
            currentStep.put("output", output);
        }
        return output;
    }

    protected Gson gson() {
        return new GsonBuilder().create();
    }
}
