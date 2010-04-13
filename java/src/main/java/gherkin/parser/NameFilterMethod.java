package gherkin.parser;

import java.util.List;

public class NameFilterMethod implements IFilterMethod {

	private Sexp sexp;
	private List filters;

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
//		for ( iterable_element : iterable) {
//			
//		}
		return false;
	}

}
