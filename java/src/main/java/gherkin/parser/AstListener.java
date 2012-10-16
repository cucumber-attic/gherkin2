package gherkin.parser;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Builder;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DocString;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.StepContainer;
import gherkin.formatter.model.Tag;
import gherkin.lexer.Listener;

import java.util.List;

public class AstListener implements Listener {
    private final Stash stash = new Stash();

    private Feature feature;
    private Background background;
    private StepContainer stepContainer;

    private Builder currentBuilder;

    @Override
    public void comment(String comment, Integer line) {
        stash.comment(new Comment(comment, line));
    }

    @Override
    public void tag(String tag, Integer line) {
        stash.tag(new Tag(tag, line));
    }

    @Override
    public void feature(String keyword, String name, String description, Integer line) {
        feature = new Feature(stash.comments, stash.tags, keyword, name, description, line, stash.featureId(name));
        stash.reset();
        background = null;
    }

    @Override
    public void background(String keyword, String name, String description, Integer line) {
        background = new Background(stash.comments, keyword, name, description, line);
        stash.reset();
        stepContainer = background;
    }

    @Override
    public void scenario(String keyword, String name, String description, Integer line) {
        replayStepsOrExamples();
        Scenario scenario = new Scenario(stash.comments, stash.tags, keyword, name, description, line, stash.featureElementId(name), background);
        stash.reset();
        stepContainer = scenario;
        feature.addStepContainer(scenario);
    }

    @Override
    public void scenarioOutline(String keyword, String name, String description, Integer line) {
        replayStepsOrExamples();
        ScenarioOutline scenarioOutline = new ScenarioOutline(stash.comments, stash.tags, keyword, name, description, line, stash.featureElementId(name), background);
        stash.reset();
        stepContainer = scenarioOutline;
        feature.addStepContainer(scenarioOutline);
    }

    @Override
    public void examples(String keyword, String name, String description, Integer line) {
        replayStepsOrExamples();
        currentBuilder = new Examples.ExamplesBuilder(stash.comments, stash.tags, keyword, name, description, line, stash.examplesId(name));
        stash.reset();
    }

    @Override
    public void step(String keyword, String name, Integer line) {
        replayStepsOrExamples();
        currentBuilder = new Step.StepBuilder(stash.comments, keyword, name, line);
        stash.reset();
    }

    @Override
    public void row(List<String> cells, Integer line) {
        currentBuilder.row(stash.comments, cells, line, stash.nextExampleId());
        stash.reset();
    }

    @Override
    public void docString(String contentType, String content, Integer line) {
        currentBuilder.docString(new DocString(contentType, content, line));
    }

    @Override
    public void eof() {
        replayStepsOrExamples();
    }

    public Feature getFeature() {
        return feature;
    }

    private void replayStepsOrExamples() {
        if (currentBuilder != null) {
            currentBuilder.populateStepContainer(stepContainer);
            currentBuilder = null;
        }
    }
}
