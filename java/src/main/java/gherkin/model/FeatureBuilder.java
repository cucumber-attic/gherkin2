package gherkin.model;

import gherkin.Listener;

import java.util.ArrayList;
import java.util.List;

public class FeatureBuilder implements Listener {
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Tag> tags = new ArrayList<Tag>();
    private Step step;
    private Feature feature;
    private ScenarioOutline scenarioOutline;
    private FeatureElement featureElement;
    private RowContainer rowContainer;
    private Examples examples;

    public void comment(String comment, int line) {
        comments.add(new Comment(comment, line));
    }

    public void tag(String tag, int line) {
        tags.add(new Tag(tag, line));
    }

    public void feature(String keyword, String name, String description, int line) {
        feature = new Feature(grabComments(), grabTags(), keyword, name, description, line);
        featureElement = null;
    }

    public void background(String keyword, String name, String description, int line) {
        FeatureElement prev = featureElement;
        featureElement = feature.newBackground(grabComments(), keyword, name, description, line);
        if (prev != null) {
            prev.next = featureElement;
        } else {
            feature.featureElement = featureElement;
        }
        step = null;
    }

    public void scenario(String keyword, String name, String description, int line) {
        FeatureElement prev = featureElement;
        featureElement = feature.newScenario(grabComments(), grabTags(), keyword, name, description, line);
        if (prev != null) {
            prev.next = featureElement;
        } else {
            feature.featureElement = featureElement;
        }
        step = null;
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        scenarioOutline = feature.newScenarioOutline(grabComments(), grabTags(), keyword, name, description, line);
        FeatureElement prev = featureElement;
        featureElement = scenarioOutline;
        if (prev != null) {
            prev.next = featureElement;
        } else {
            feature.featureElement = featureElement;
        }
        step = null;
    }

    public void examples(String keyword, String name, String description, int line) {
        Examples prev = examples;
        examples = scenarioOutline.newExamples(grabComments(), grabTags(), keyword, name, description, line);
        if (prev != null) {
            prev.next = examples;
        }else {
            scenarioOutline.examples = examples;
        }
        rowContainer = examples;
    }

    public void step(String keyword, String name, int line) {
        Step prev = step;
        step = featureElement.newStep(grabComments(), keyword, name, line);
        if (prev != null) {
            prev.next = step;
        } else {
            featureElement.step = step;
        }
        rowContainer = step;
    }

    public void row(List<String> cells, int line) {
        Row row = new Row(grabComments(), cells, line);
        rowContainer.addRow(row);
    }

    public void pyString(String string, int line) {
        PyString pyString = new PyString(string, line);
        step.setMultilineArg(pyString);
    }

    public void eof() {
    }

    private List<Comment> grabComments() {
        List<Comment> comments = this.comments;
        this.comments = new ArrayList<Comment>();
        return comments;
    }

    private List<Tag> grabTags() {
        List<Tag> tags = this.tags;
        this.tags = new ArrayList<Tag>();
        return tags;
    }

    public Feature getFeature() {
        return feature;
    }
}
