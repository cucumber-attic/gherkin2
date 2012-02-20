package gherkin.formatter;

import com.google.gson.Gson;
import gherkin.JSONParser;
import org.junit.Test;

import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
        String json = "" +
                "[\n" +
                "  {\n" +
                "    \"id\": \"one\",\n" +
                "    \"uri\": \"test.feature\",\n" +
                "    \"tags\": [\n" +
                "      {\n" +
                "        \"name\": \"@foo\",\n" +
                "        \"line\": 22\n" +
                "      }\n" +
                "    ],\n" +
                "    \"keyword\": \"Feature\", \n" +
                "    \"name\": \"One\", \n" +
                "    \"description\": \"\", \n" +
                "    \"line\": 3,\n" +
                "    \"elements\": [\n" +
                "      {\n" +
                "        \"id\": \"one/a-scenario\",\n" +
                "        \"type\": \"scenario\",\n" +
                "        \"steps\": [\n" +
                "          {\n" +
                "            \"keyword\": \"Given \",\n" +
                "            \"name\": \"a passing step\",\n" +
                "            \"line\": 6,\n" +
                "            \"match\": {\n" +
                "              \"arguments\": [\n" +
                "                {\n" +
                "                  \"offset\": 22,\n" +
                "                  \"val\": \"cukes\"\n" +
                "                }\n" +
                "              ],\n" +
                "              \"location\": \"features/step_definitions/steps.rb:1\"\n" +
                "            },\n" +
                "            \"result\": {\n" +
                "              \"status\": \"failed\",\n" +
                "              \"error_message\": \"You suck\",\n" +
                "              \"duration\": -1\n" +
                "            },\n" +
                "            \"embeddings\": [\n" +
                "              {\n" +
                "                \"mime_type\": \"text/plain\",\n" +
                "                \"data\": \"Tm8sIEknbSBub3QgaW50ZXJlc3RlZCBpbiBkZXZlbG9waW5nIGEgcG93ZXJmdWwgYnJhaW4uIEFsbCBJJ20gYWZ0ZXIgaXMganVzdCBhIG1lZGlvY3JlIGJyYWluLCBzb21ldGhpbmcgbGlrZSB0aGUgUHJlc2lkZW50IG9mIHRoZSBBbWVyaWNhbiBUZWxlcGhvbmUgYW5kIFRlbGVncmFwaCBDb21wYW55Lg==\"\n" +
                "              }\n" +
                "            ],\n" +
                "            \"output\": [\n" +
                "              \"Hello\",\n" +
                "              \"World\"\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
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

}
