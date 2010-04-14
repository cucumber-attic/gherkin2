package gherkin.parser;

import java.util.List;
import java.util.regex.Pattern;

public class NameFilterMethod implements IFilterMethod {

	private List<Pattern> filters;

	@Override
	public boolean filterTags(List currentTags) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFilters(List filters) {
		this.filters = filters;
	}

	@Override
	public boolean filter(Sexp sexp) {
		for (Pattern filter : filters) {
			if (filter.matcher(sexp.getName()).matches()){
				System.out.println("Filter [" + filter + "] Matched: [" + sexp.getName() + "]");
				return true;
			}
			System.out.println("Filter [" + filter + "] No Match Matched: [" + sexp.getName() + "]");
		}
		return false;
	}

}
