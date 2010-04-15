package gherkin.parser;

import java.util.List;
import java.util.regex.Pattern;

public class NameFilterMethod implements IFilterMethod {

	private List<Pattern> filters;

	public boolean filterTags(List currentTags) {
		return false;
	}

	@SuppressWarnings("unchecked")
	public void setFilters(List filters) {
		this.filters = filters;
	}

	public boolean filter(Sexp sexp) {
		for (Pattern filter : filters) {
            if(sexp.matches(filter)) {
                return true;
            }
		}
		return false;
	}

}
