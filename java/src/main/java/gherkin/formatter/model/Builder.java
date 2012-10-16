package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public interface Builder {
    void row(List<Comment> comments, List<String> cells, Integer line, String id);

    void docString(DocString docString);

    void replay(Formatter formatter);

    void populateStepContainer(StepContainer stepContainer);
}
