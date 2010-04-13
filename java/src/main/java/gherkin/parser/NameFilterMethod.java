package gherkin.parser;

import java.util.List;

public class NameFilterMethod implements IFilterMethod {

	@Override
	public boolean eval(List currentTags) {
		return false;
	}

	@Override
	public void setFilters(List filters) {
	}

}
