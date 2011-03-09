package gherkin.formatter;

import gherkin.model.Range;
import gherkin.model.Row;
import gherkin.model.Tag;

import java.util.List;

public interface Filter {
    boolean eval(List<Tag> tags, List<String> names, List<Range> ranges);

    List<Row> filterTableBodyRows(List<Row> examplesRows);
}
