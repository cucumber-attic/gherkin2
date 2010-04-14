package gherkin.parser;

import java.util.List;

public class LineFilterMethod implements IFilterMethod {

	private List<Long> filters;

	@Override
	public boolean filterTags(List currentTags) {
		return false;
	}

	@Override
	public void setFilters(List filters) {
		this.filters = (List<Long>)filters;
	}

	@Override
	public boolean filter(Sexp sexp) {
		for (Long filterItem : filters) {
			if (filterItem.toString().equals(sexp.getLine().toString())){
				return true;
			}
		}
		return false;
	}

}
