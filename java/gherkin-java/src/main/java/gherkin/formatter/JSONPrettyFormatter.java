package gherkin.formatter;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;

public class JSONPrettyFormatter extends JSONFormatter {
    public JSONPrettyFormatter(Appendable out) {
        super(out);
    }

    @Override
    protected Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
