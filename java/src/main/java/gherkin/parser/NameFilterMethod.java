package gherkin.parser;

import java.util.List;

public class NameFilterMethod implements IFilterMethod {

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
		for (Object item : filters) {
			System.out.println("NameFilterMethod: " + item.getClass().getName() + ", " + item);
		}
		return false;
	}

}
