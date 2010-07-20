package gherkin;

import gherkin.formatter.Formatter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONParser {
    private final Formatter formatter;
    private Listener listener;

    public JSONParser(Formatter formatter) {
        this.formatter = formatter;
    }

    public void parseWithListener(String src, Listener listener) {
        this.listener = listener;
        parse(src, "unknown.json", 0);
    }

    private void parse(String src, String uri, int offset) {
        listener.
        JSONObject feature = (JSONObject) JSONValue.parse(src);
    }
}
