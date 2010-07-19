package gherkin.formatter;

import gherkin.formatter.model.Range;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Tag;

import java.util.List;

public interface Filter {
    boolean eval(List<Tag> tags, List<String> names, List<Range> ranges);

    List<Row> filterTableBodyRows(List<Row> examplesRows);
}
