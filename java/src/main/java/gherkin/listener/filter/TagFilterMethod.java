package gherkin.listener.filter;

import gherkin.TagExpression;

import java.util.List;

public class TagFilterMethod implements FilterMethod {

    private final TagExpression tagExpression;

    public TagFilterMethod(List<String> filters) {
        this.tagExpression = new TagExpression(filters);
    }

    @SuppressWarnings("unchecked")
    public boolean filterTags(List currentTags) {
        return tagExpression.eval(currentTags);
    }

    public boolean filter(Event event) {
        return false;
    }

}
