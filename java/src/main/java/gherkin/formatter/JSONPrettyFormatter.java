package gherkin.formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONPrettyFormatter extends JSONFormatter {
    public JSONPrettyFormatter(Appendable out) {
        super(out);
    }

    @Override
    protected Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
