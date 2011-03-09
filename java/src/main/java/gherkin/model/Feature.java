package gherkin.model;

import gherkin.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Feature extends DescribedStatement {
    private final List<FeatureElement> featureElements = new ArrayList<FeatureElement>();
    public FeatureElement featureElement;

    public Feature(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        super(comments, tags, keyword, name, description, line);
    }

    @Override
    public void replay(Formatter formatter) {
        formatter.feature(this);
    }

    public Scenario newScenario(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        Scenario scenario = new Scenario(comments, tags, keyword, name, description, line);
        featureElements.add(scenario);
        return scenario;
    }

    public ScenarioOutline newScenarioOutline(List<Comment> comments, List<Tag> tags, String keyword, String name, String description, int line) {
        ScenarioOutline scenarioOutline = new ScenarioOutline(comments, tags, keyword, name, description, line);
        featureElements.add(scenarioOutline);
        return scenarioOutline;
    }

    public Background newBackground(List<Comment> comments, String keyword, String name, String description, int line) {
        Background background = new Background(comments, keyword, name, description, line);
        featureElements.add(background);
        return background;
    }

    /**
     * Traverses the internal structure, depth first.
     * @param v the visitor that will receive callbacks.
     */
    public void accept(Visitor v) {
        for (FeatureElement featureElement : featureElements) {
            featureElement.accept(v);
        }
        v.visitFeature(this);
    }
    
    public void accept(final NewVisitor v) {
        It it = new It(){
            public void next() {
                featureElement.accept(v);
            }
        };
        v.visitFeature(this, it);
    }
}
