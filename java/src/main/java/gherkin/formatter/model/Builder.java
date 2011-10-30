package gherkin.formatter.model;

import gherkin.formatter.Formatter;

import java.util.List;

public interface Builder {
    void row(List<Comment> comments, List<String> cells, int line);

    void docString(DocString docString);

    void replay(Formatter formatter);
}
