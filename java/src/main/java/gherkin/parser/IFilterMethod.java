package gherkin.parser;

import java.util.List;

public interface IFilterMethod {

	void setFilters(List filters);
	boolean eval(List currentTags);

}
