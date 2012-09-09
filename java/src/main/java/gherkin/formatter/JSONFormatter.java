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

    private enum Phase {step, match, result, embedding, output};
    
    /**
     * In order to handle steps being added all at once, this method determines allows methods to
     * opperator correctly if 
     * 
     * step
     * match
     * result
     * embedding
     * output
     * step
     * match
     * result
     * embedding
     * output
     * 
     * or if 
     * 
     * step
     * step
     * match
     * result
     * embedding
     * output
     * match
     * result
     * embedding
     * output
     * 
     * is called
     * 
     * @return the correct step for the current operation based on past method calls to the formatter interface
     */
    private Map getCurrentStep(Phase phase){
    	String target = phase.ordinal() <= Phase.match.ordinal()?Phase.match.name():Phase.result.name();
        boolean lastWith = false;
        lastWith = (phase.ordinal() > Phase.result.ordinal());
        Map lastWithValue = null;
    	for (Map stepOrHook : getSteps()){
    		if (stepOrHook.get(target) == null){
    			if (lastWith){
       			    return lastWithValue;
    			} else {
    				return stepOrHook;
    			}
    		} else {
    			lastWithValue = stepOrHook;
    		}
    	}
    	return lastWithValue;
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
        getEmbeddings().add(embedding);
    }

    @Override
    public void write(String text) {
        getOutput().add(text);
    }

    @Override
    public void result(Result result) {
    	getCurrentStep(Phase.result).put("result", result.toMap());
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
        List<Map> hooks = getFeatureElement().get(hook);
        if (hooks == null) {
            hooks = new ArrayList<Map>();
            getFeatureElement().put(hook, hooks);
        }
        Map hookMap = new HashMap();
        hookMap.put("match", match.toMap());
        hookMap.put("result", result.toMap());
        hooks.add(hookMap);
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

    private Map<Object, List<Map>> getFeatureElement() {
        return (Map) getFeatureElements().get(getFeatureElements().size() - 1);
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

    private List<Map<String, String>> getEmbeddings() {
        List<Map<String, String>> embeddings = (List<Map<String, String>>) getCurrentStep(Phase.embedding).get("embeddings");
        if (embeddings == null) {
            embeddings = new ArrayList<Map<String, String>>();
            getCurrentStep(Phase.embedding).put("embeddings", embeddings);
        }
        return embeddings;
    }

    private List<String> getOutput() {
        List<String> output = (List<String>) getCurrentStep(Phase.output).get("output");
        if (output == null) {
            output = new ArrayList<String>();
            getCurrentStep(Phase.output).put("output", output);
        }
        return output;
    }

    protected Gson gson() {
        return new GsonBuilder().create();
    }
}
