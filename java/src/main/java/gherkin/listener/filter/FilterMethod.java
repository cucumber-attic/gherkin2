package gherkin.listener.filter;

import java.util.List;

public interface FilterMethod {

    boolean filterTags(List currentTags);

    boolean filter(Event event);

}
