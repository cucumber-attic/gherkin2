package gherkin.parser;

import java.util.List;

public class TagFilterMethod implements IFilterMethod {

	private TagExpression tagExpression;

	@SuppressWarnings("unchecked")
	@Override
	public boolean filterTags(List currentTags) {
		return tagExpression.eval(currentTags);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFilters(List filters) {
		this.tagExpression = new TagExpression(filters);
	}

	@Override
	public boolean filter(Sexp sexp) {
		return false;
	}

}
