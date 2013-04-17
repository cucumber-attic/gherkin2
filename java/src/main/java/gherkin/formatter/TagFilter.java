package gherkin.formatter;

import gherkin.TagExpression;
import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Tag;

import java.util.List;

public class TagFilter implements Filter {
    private final TagExpression tagExpression;

    public TagFilter(List<String> tags) {
        tagExpression = new TagExpression(tags);
    }

    public boolean evaluate(List<Tag> tags, List<String> names, List<Range> ranges) {
        return tagExpression.evaluate(tags);
    }

    public List<ExamplesTableRow> filterTableBodyRows(List<ExamplesTableRow> examplesRows) {
        return examplesRows;
    }
}
