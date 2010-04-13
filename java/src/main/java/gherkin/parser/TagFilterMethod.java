package gherkin.parser;

import java.util.List;

public class TagFilterMethod implements IFilterMethod {

	private List filters;

	@Override
	public boolean filterTags(List currentTags) {
		List<String> tags = (List<String>)currentTags;
		for (String tag : tags) {
			if (filters.contains(tag)){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFilters(List filters) {
		this.filters = filters;
	}

	@Override
	public boolean filter(Sexp sexp) {
		return false;
	}

}
