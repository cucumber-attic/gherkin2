package gherkin.formatter;

import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Tag;

import java.util.List;

public interface Filter {
    boolean evaluate(List<Tag> tags, List<String> names, List<Range> ranges);

    List<ExamplesTableRow> filterTableBodyRows(List<ExamplesTableRow> examplesRows);
}
