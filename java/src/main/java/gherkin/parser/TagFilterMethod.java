package gherkin.parser;

import java.util.List;

public class TagFilterMethod implements IFilterMethod {

	private TagExpression tagExpression;

	@SuppressWarnings("unchecked")
	public boolean filterTags(List currentTags) {
		return tagExpression.eval(currentTags);
	}

	@SuppressWarnings("unchecked")
	public void setFilters(List filters) {
		this.tagExpression = new TagExpression(filters);
	}

	public boolean filter(Sexp sexp) {
		return false;
	}

}
