package gherkin.listener.filter;

import java.util.List;
import java.util.regex.Pattern;

public class NameFilterMethod implements FilterMethod {

    private final List<Pattern> filters;

    public NameFilterMethod(List<Pattern> filters) {
        this.filters = filters;
    }

    public boolean filterTags(List currentTags) {
        return false;
    }

    public boolean filter(Event event) {
        for (Pattern filter : filters) {
            if (event.matches(filter)) {
                return true;
            }
        }
        return false;
    }

}
