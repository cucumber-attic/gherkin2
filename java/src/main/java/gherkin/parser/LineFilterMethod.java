package gherkin.parser;

import java.util.List;

public class LineFilterMethod implements IFilterMethod {

    private List<Number> filters;

    public boolean filterTags(List currentTags) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public void setFilters(List filters) {
        this.filters = (List<Number>) filters;
    }

    public boolean filter(Sexp sexp) {
        for (Number filterItem : filters) {
            if (filterItem.toString().equals(sexp.getLine().toString())) {
                return true;
            }
        }
        return false;
    }

}
