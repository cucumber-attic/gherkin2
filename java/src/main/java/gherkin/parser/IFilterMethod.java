package gherkin.parser;

import java.util.List;

public interface IFilterMethod {

    void setFilters(List filters);

    boolean filterTags(List currentTags);

    boolean filter(Sexp sexp);

}
