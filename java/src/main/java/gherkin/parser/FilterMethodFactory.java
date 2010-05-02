package gherkin.parser;

import java.util.List;
import java.util.regex.Pattern;

public class FilterMethodFactory {

    public FilterMethod getFilterMethod(List filters) {
        Class<?> typeOfFilter = filters.get(0).getClass();
        if (String.class.isAssignableFrom(typeOfFilter)) {
            return new TagFilterMethod(filters);
        } else if (Number.class.isAssignableFrom(typeOfFilter)) {
            return new LineFilterMethod(filters);
        } else if (Pattern.class.isAssignableFrom(typeOfFilter)) {
            return new NameFilterMethod(filters);
        } else {
            throw new RuntimeException("Could not create filter method for unknown filter of type: " + typeOfFilter);
        }
    }

}
