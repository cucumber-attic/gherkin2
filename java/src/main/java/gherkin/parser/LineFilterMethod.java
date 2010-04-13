package gherkin.parser;

import java.util.List;

public class LineFilterMethod implements IFilterMethod {

	@Override
	public boolean eval(List currentTags) {
		return false;
	}

	@Override
	public void setFilters(List filters) {
	}

}
