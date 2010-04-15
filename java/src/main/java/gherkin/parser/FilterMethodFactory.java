package gherkin.parser;

import java.util.regex.Pattern;

public class FilterMethodFactory {

	public IFilterMethod getFilterMethod(Class<?> typeOfFilter) {
		if (String.class.isAssignableFrom(typeOfFilter)){
			return new TagFilterMethod();
		}
		else if (Number.class.isAssignableFrom(typeOfFilter)){
			return new LineFilterMethod();
		}
		else if (Pattern.class.isAssignableFrom(typeOfFilter)){
			return new NameFilterMethod();
		}
		else{
			throw new RuntimeException("Could not create filter method for unknown filter of type: " + typeOfFilter);
		}
	}

}
