package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public class Feature extends TagStatement {
    public Feature(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.feature(this);
    }
}
