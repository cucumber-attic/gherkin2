package gherkin.parser;

import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class Stash {
    public List<Comment> comments;
    public List<Tag> tags;

    public String featureId;
    public String featureElementId;
    public String examplesId;
    public int rowIndex = 0;

    public void comment(Comment comment) {
        comments.add(comment);
    }

    public void tag(Tag tag) {
        tags.add(tag);
    }

    public void reset() {
        comments = new ArrayList<Comment>();
        tags = new ArrayList<Tag>();
    }

    public String featureId(String name) {
        return featureId = id(name);
    }

    public String featureElementId(String name) {
        return featureElementId = featureId + ";" + id(name);
    }

    public String examplesId(String name) {
        rowIndex = 0;
        return examplesId = featureElementId + ";" + id(name);
    }

    private String id(String name) {
        return name.replaceAll("[\\s_]", "-").toLowerCase();
    }

    public String nextExampleId() {
        rowIndex++;
        return "" + examplesId + ";" + rowIndex;
    }
}
