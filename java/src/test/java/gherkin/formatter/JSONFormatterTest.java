package gherkin.formatter;

import static gherkin.util.FixJava.readResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import gherkin.JSONParser;
import gherkin.deps.com.google.gson.Gson;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DocString;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

import java.io.PrintStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class JSONFormatterTest {
    @Test
    public void shouldNotCloseProvidedStreamInDone() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new JSONFormatter(out);
        formatter.done();
        verify(out, never()).close();
    }

    @Test
    public void shouldFlushAndCloseProvidedStreamInClose() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new JSONFormatter(out);
        formatter.close();
        verify(out).flush();
        verify(out).close();
    }

    @Test
    public void should_roundtrip_this_json() {
        // From json_parser_spec.rb
        String json = readResource("/gherkin/formatter/model/complicated.json");
        checkJson(json);
    }

    private void checkJson(String json) {
        Appendable io = new StringBuilder();
        JSONPrettyFormatter f = new JSONPrettyFormatter(io);
        JSONParser p = new JSONParser(f, f);
        p.parse(json);
        f.done();

        Gson gson = new Gson();

        List expected = gson.fromJson(new StringReader(json), List.class);
        List actual = gson.fromJson(new StringReader(io.toString()), List.class);
        assertEquals(expected, actual);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testAllStepsFirstOrderingOfCalls() {
        StringBuilder stringBuilder = new StringBuilder();
        JSONFormatter jsonFormatter = new JSONPrettyFormatter(stringBuilder);
        Feature feature = feature("Test Feature");
        Scenario scenario = scenario("Test Scenario");
        Step step1 = step("Given", "Step 1");
        Step step2 = step("Given", "Step 2");
        final byte[] data1 = new byte[] {1, 2, 3};
        String text1 = "text1";
        String text2 = "text2";
        final byte[] data2 = new byte[] {4};
        Result step1Result = result("passed");
        Result step2Result = result("failed");

        jsonFormatter.uri(uri());
        jsonFormatter.feature(feature);
        jsonFormatter.startOfScenarioLifeCycle(scenario);
        jsonFormatter.before(match(), result("passed"));
        jsonFormatter.scenario(scenario);

        jsonFormatter.step(step1);
        jsonFormatter.step(step2);

        jsonFormatter.match(match());
        jsonFormatter.embedding("mime-type", data1);
        jsonFormatter.write(text1);
        jsonFormatter.result(step1Result);

        jsonFormatter.match(match());
        jsonFormatter.write(text2);
        jsonFormatter.embedding("mime-type-2", data2);
        jsonFormatter.result(step2Result);
        jsonFormatter.endOfScenarioLifeCycle(scenario);

        jsonFormatter.eof();
        jsonFormatter.done();
        jsonFormatter.close();

        Gson gson = new Gson();
        List result = gson.fromJson(stringBuilder.toString(), List.class);

        Map featureJson = (Map) result.get(0);
        assertEquals(feature.getName(), featureJson.get("name"));

        Map scenarioJson = (Map) ((List) featureJson.get("elements")).get(0);
        assertEquals(scenario.getName(), scenarioJson.get("name"));

        Map step1Json = (Map) ((List)scenarioJson.get("steps")).get(0);
        assertJsonStepData(step1, step1Result, step1Json);

        Map step2Json = (Map) ((List)scenarioJson.get("steps")).get(1);
        assertJsonStepData(step2, step2Result, step2Json);


    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testOneStepAtTheTimeOrderingOfCalls() {
        StringBuilder stringBuilder = new StringBuilder();
        JSONFormatter jsonFormatter = new JSONPrettyFormatter(stringBuilder);
        Feature feature = feature("Test Feature");
        Scenario scenario = scenario("Test Scenario");
        Step step1 = step("Given", "Step 1");
        Step step2 = step("Given", "Step 2");
        final byte[] data1 = new byte[] {1, 2, 3};
        String text1 = "text1";
        String text2 = "text2";
        final byte[] data2 = new byte[] {4};
        Result step1Result = result("passed");
        Result step2Result = result("failed");

        jsonFormatter.uri(uri());
        jsonFormatter.feature(feature);
        jsonFormatter.startOfScenarioLifeCycle(scenario);
        jsonFormatter.before(match(), result("passed"));
        jsonFormatter.scenario(scenario);

        jsonFormatter.step(step1);
        jsonFormatter.match(match());
        jsonFormatter.embedding("mime-type", data1);
        jsonFormatter.write(text1);
        jsonFormatter.result(step1Result);

        jsonFormatter.step(step2);
        jsonFormatter.match(match());
        jsonFormatter.write(text2);
        jsonFormatter.embedding("mime-type-2", data2);
        jsonFormatter.result(step2Result);
        jsonFormatter.endOfScenarioLifeCycle(scenario);

        jsonFormatter.eof();
        jsonFormatter.done();
        jsonFormatter.close();

        Gson gson = new Gson();
        List result = gson.fromJson(stringBuilder.toString(), List.class);

        Map featureJson = (Map) result.get(0);
        assertEquals(feature.getName(), featureJson.get("name"));

        Map scenarioJson = (Map) ((List) featureJson.get("elements")).get(0);
        assertEquals(scenario.getName(), scenarioJson.get("name"));

        Map step1Json = (Map) ((List)scenarioJson.get("steps")).get(0);
        assertJsonStepData(step1, step1Result, step1Json);

        Map step2Json = (Map) ((List)scenarioJson.get("steps")).get(1);
        assertJsonStepData(step2, step2Result, step2Json);


    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testBeforeHooks() {
        StringBuilder stringBuilder = new StringBuilder();
        JSONFormatter jsonFormatter = new JSONPrettyFormatter(stringBuilder);
        Feature feature = feature("Test Feature");
        Scenario scenario1 = scenario("Test Scenario 1");
        Scenario scenario2 = scenario("Test Scenario 2");

        jsonFormatter.uri(uri());
        jsonFormatter.feature(feature);

        jsonFormatter.startOfScenarioLifeCycle(scenario1);
        jsonFormatter.before(match(), result("passed"));
        jsonFormatter.scenario(scenario1);
        jsonFormatter.endOfScenarioLifeCycle(scenario1);

        jsonFormatter.startOfScenarioLifeCycle(scenario2);
        jsonFormatter.before(match(), result("passed"));
        jsonFormatter.scenario(scenario2);
        jsonFormatter.endOfScenarioLifeCycle(scenario2);

        jsonFormatter.eof();
        jsonFormatter.done();
        jsonFormatter.close();

        Gson gson = new Gson();
        List result = gson.fromJson(stringBuilder.toString(), List.class);

        Map featureJson = (Map) result.get(0);
        assertEquals(feature.getName(), featureJson.get("name"));

        Map scenarioJson = (Map) ((List) featureJson.get("elements")).get(0);
        assertEquals(scenario1.getName(), scenarioJson.get("name"));

        List beforeHooks = (List) scenarioJson.get("before");
        assertEquals(1, beforeHooks.size());

        scenarioJson = (Map) ((List) featureJson.get("elements")).get(1);
        assertEquals(scenario2.getName(), scenarioJson.get("name"));

        beforeHooks = (List) scenarioJson.get("before");
        assertEquals(1, beforeHooks.size());
    }

	private void assertJsonStepData(Step step, Result stepResult, Map stepJson) {
		assertEquals(step.getName(), stepJson.get("name"));
        List embeddings1 = (List)stepJson.get("embeddings");
        assertNotNull("embeddings", embeddings1);
        assertEquals("embeddings size", embeddings1.size(), 1);
        List output1 = (List)stepJson.get("output");
        assertNotNull("output", output1);
        assertEquals("output size", output1.size(), 1);
        Map step1ResultJson = (Map) stepJson.get("result");
        assertEquals(stepResult.getStatus(), step1ResultJson.get("status"));
	}

    private String uri() {
        return "uri";
    }

    private Feature feature(String featureName) {
        return new Feature(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "", featureName, "", 12, "");
    }

    private Scenario scenario(String scenarioName) {
    	return new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "", scenarioName, "", 13, "");
    }

    private Step step(String keyword, String stepName) {
        return new Step(Collections.<Comment>emptyList(), keyword, stepName, 14, null, new DocString("", "", 52));
    }

    private Match match() {
        return new Match(null, null);
    }

    private Result result(String status) {
        return new Result(status, 565L, "");
    }

}
