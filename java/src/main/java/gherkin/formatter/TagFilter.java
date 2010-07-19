package gherkin.formatter;

import gherkin.TagExpression;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagFilter implements Filter {
    private final TagExpression tagExpression;

    public TagFilter(List<String> tags) {
        tagExpression = new TagExpression(tags);
    }

    public boolean eval(List<Tag> tags, List<String> names, List<Range> ranges) {
        List<String> tagNames = new ArrayList<String>();
        for(Tag tag:tags) {
            tagNames.add(tag.getName());
        }
        return tagExpression.eval(tagNames);
    }

    public List<Row> filterTableBodyRows(List<Row> examplesRows) {
        return examplesRows;
    }
}
