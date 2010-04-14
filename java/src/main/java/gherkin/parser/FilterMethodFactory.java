package gherkin.parser;

import java.util.regex.Pattern;

public class FilterMethodFactory {

	public IFilterMethod getFilterMethod(Class<? extends Object> typeOfFilter) {
		if (String.class == typeOfFilter){
			return new TagFilterMethod();
		}
		else if (Long.class == typeOfFilter){
			return new LineFilterMethod();
		}
		else if (Pattern.class == typeOfFilter){
			return new NameFilterMethod();
		}
		else{
			throw new RuntimeException("Could not create filter method for unknown filter of type: " + typeOfFilter);
		}
	}

}
