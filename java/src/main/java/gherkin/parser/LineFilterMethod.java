package gherkin.parser;

import java.util.List;

public class LineFilterMethod implements FilterMethod {

    private final List<Number> filters;

    public LineFilterMethod(List<Number> filters) {
        this.filters = filters;
    }

    public boolean filterTags(List currentTags) {
        return false;
    }

    public boolean filter(Event event) {
        for (Number filterItem : filters) {
            if (filterItem.toString().equals(event.getLine().toString())) {
                return true;
            }
        }
        return false;
    }

}
