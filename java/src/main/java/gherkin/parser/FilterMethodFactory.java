package gherkin.parser;

public class FilterMethodFactory {

	public IFilterMethod getFilterMethod(Class<? extends Object> typeOfFilter) {
		if (String.class == typeOfFilter){
			return new TagFilterMethod();
		}
		else if (Long.class == typeOfFilter){
			return new LineFilterMethod();
		}
//		else if (RubyRegexp.class == typeOfFilter){
//			return new NameFilterMethod();
//		}
		else{
			return new NameFilterMethod();
//			throw new RuntimeException("Could not create filter method for unknown filter of type: " + typeOfFilter);
		}
	}

}
