package gherkin.formatter;

import static gherkin.util.FixJava.readResource;
import static org.junit.Assert.assertEquals;
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
import java.util.ArrayList;
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
    public void testOrderingOfCalls() {
        StringBuilder stringBuilder = new StringBuilder();
        JSONFormatter jsonFormatter = new JSONPrettyFormatter(stringBuilder);

        Feature feature = new Feature(new ArrayList<Comment>(), new ArrayList<Tag>(), "", "Test Feature", "", 12, "");
        jsonFormatter.feature(feature);
        Scenario scenario = new Scenario(new ArrayList<Comment>(), new ArrayList<Tag>(), "", "Test Scenario", "", 13, "");
        jsonFormatter.scenario(scenario);
        
        Step step1 = new Step(new ArrayList<Comment>(), "Given", "Step 1", 14, null, new DocString("", "", 52));
        jsonFormatter.step(step1 );
        
        Step step2 = new Step(new ArrayList<Comment>(), "Given", "Step 2", 15, null, new DocString("", "", 52));
        jsonFormatter.step(step2 );
        
        jsonFormatter.match(new Match(new ArrayList<Argument>(), "xx"));
        
        Result step1Result = new Result("passed", 565L, "");
        jsonFormatter.result(step1Result);
        
        jsonFormatter.match(new Match(new ArrayList<Argument>(), "yy"));
        
        Result step2Result = new Result("failed", 45L, "");
        jsonFormatter.result(step2Result);
        
        jsonFormatter.done();
        jsonFormatter.close();
        System.out.println(stringBuilder.toString());
        
        
        Gson gson = new Gson();
        List result = gson.fromJson(stringBuilder.toString(), List.class);

        Map featureJson = (Map) result.get(0);
        assertEquals(feature.getName(), featureJson.get("name"));

        Map scenarioJson = (Map) ((List) featureJson.get("elements")).get(0);
        assertEquals(scenario.getName(), scenarioJson.get("name"));
        
        Map step1Json = (Map) ((List)scenarioJson.get("steps")).get(0);
        assertEquals(step1.getName(), step1Json.get("name"));
        
        Map step2Json = (Map) ((List)scenarioJson.get("steps")).get(1);
        assertEquals(step2.getName(), step2Json.get("name"));
        
        Map step1ResultJson = (Map) step1Json.get("result");
        assertEquals(step1Result.getStatus(), step1ResultJson.get("status"));
        
        Map step2ResultJson = (Map) step2Json.get("result");
        assertEquals(step2Result.getStatus(), step2ResultJson.get("status"));
        
        
    }

}