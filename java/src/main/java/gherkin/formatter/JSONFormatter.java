package gherkin.formatter;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.net.iharder.Base64;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFormatter implements Reporter, Formatter {
    private final List<Map<String, Object>> featureMaps = new ArrayList<Map<String, Object>>();
    private final NiceAppendable out;

    private Map<String, Object> featureMap;
    private String uri;
    private Map<Object, List<Map>> beforeHookScenario = new HashMap<Object, List<Map>>();
    private Map<String, Object> currentHook = new HashMap<String, Object>();

    private enum Phase {step, match, embedding, output, result};

    /**
     * In order to handle steps being added all at once, this method determines allows methods to
     * opperator correctly if
     *
     * step
     * match
     * embedding
     * output
     * result
     * step
     * match
     * embedding
     * output
     * result
     *
     * or if
     *
     * step
     * step
     * match
     * embedding
     * output
     * result
     * match
     * embedding
     * output
     * result
     *
     * is called
     *
     * @return the correct step for the current operation based on past method calls to the formatter interface
     */
    private Map getCurrentStep(Phase phase) {
        String target = phase.ordinal() <= Phase.match.ordinal()?Phase.match.name():Phase.result.name();
        Map<Object, List<Map>> currentScenario = getFeatureElement();
        if (currentScenario != null) {
            List<Map> steps = currentScenario.get("steps");
            if (steps != null) {
                for (Map step : steps) {
                    if (step.get(target) == null) {
                        return step;
                    }
                }
            }
        }
        return null;
    }


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
    }

    @Override
    public void background(Background background) {
        getFeatureElements().add(background.toMap());
    }

    @Override
    public void scenario(Scenario scenario) {
        getFeatureElements().add(scenario.toMap());
        if (beforeHookScenario.get("before") != null) {
            getFeatureElement().put("before", beforeHookScenario.get("before"));
            beforeHookScenario.remove("before");
        }
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
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
        getCurrentStep(Phase.match).put("match", match.toMap());
    }

    @Override
    public void embedding(String mimeType, byte[] data) {
        final Map<String, String> embedding = new HashMap<String, String>();
        embedding.put("mime_type", mimeType);
        embedding.put("data", Base64.encodeBytes(data));
        Map currentStep = getCurrentStep(Phase.embedding);
        if (currentStep != null) {
            getEmbeddings(currentStep).add(embedding);
        } else {
            getEmbeddings(currentHook).add(embedding);
        }
    }

    @Override
    public void write(String text) {
        Map currentStep = getCurrentStep(Phase.output);
        if (currentStep != null) {
            getOutput(currentStep).add(text);
        } else {
            getOutput(currentHook).add(text);
        }
    }

    @Override
    public void result(Result result) {
        getCurrentStep(Phase.result).put("result", result.toMap());
    }

    @Override
    public void before(Match match, Result result) {
        addHook(beforeHookScenario, match, result, "before");
    }

    @Override
    public void after(Match match, Result result) {
        addHook(getFeatureElement(), match, result, "after");
    }

    private void addHook(final Map<Object, List<Map>> currentScenario, final Match match, final Result result, final String hook) {
        List<Map> hooks = currentScenario.get(hook);
        if (hooks == null) {
            hooks = new ArrayList<Map>();
            currentScenario.put(hook, hooks);
        }
        hooks.add(buildHookMap(match,result));
    }

    private Map buildHookMap(final Match match, final Result result) {
        currentHook.put("match", match.toMap());
        currentHook.put("result", result.toMap());
        final Map hookMap = currentHook;
        currentHook = new HashMap<String, Object>();
        return hookMap;
    }

    public void appendDuration(final int timestamp) {
        Map result = null;
        for (Map step : getFeatureElement().get("steps")) {
            if (step.get("result") == null) {
                break;
            } else {
                result = (Map) step.get("result");
            }
        }

        // check to make sure result exists (scenario outlines do not have results yet)
        if (result != null) {
            //convert to nanoseconds
            final long nanos = timestamp * 1000000000L;
            result.put("duration", nanos);
        }
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
    public void startOfScenarioLifeCycle(Scenario scenario) {
        // NoOp
    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        // NoOp
    }

    private List<Map<String, Object>> getFeatureElements() {
        List<Map<String, Object>> featureElements = (List) featureMap.get("elements");
        if (featureElements == null) {
            featureElements = new ArrayList<Map<String, Object>>();
            featureMap.put("elements", featureElements);
        }
        return featureElements;
    }

    private Map<Object, List<Map>> getFeatureElement() {
        if (getFeatureElements().size() > 0) {
            return (Map) getFeatureElements().get(getFeatureElements().size() - 1);
        } else {
            return null;
        }
    }

    private List<Map> getAllExamples() {
        List<Map> allExamples = getFeatureElement().get("examples");
        if (allExamples == null) {
            allExamples = new ArrayList<Map>();
            getFeatureElement().put("examples", allExamples);
        }
        return allExamples;
    }

    private List<Map> getSteps() {
        List<Map> steps = getFeatureElement().get("steps");
        if (steps == null) {
            steps = new ArrayList<Map>();
            getFeatureElement().put("steps", steps);
        }
        return steps;
    }

    private List<Map<String, String>> getEmbeddings(final Map currentStep) {
        List<Map<String, String>> embeddings = (List<Map<String, String>>) currentStep.get("embeddings");
        if (embeddings == null) {
            embeddings = new ArrayList<Map<String, String>>();
            currentStep.put("embeddings", embeddings);
        }
        return embeddings;
    }

    private List<String> getOutput(final Map currentStep) {
        List<String> output = (List<String>) currentStep.get("output");
        if (output == null) {
            output = new ArrayList<String>();
            currentStep.put("output", output);
        }
        return output;
    }

    protected Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
