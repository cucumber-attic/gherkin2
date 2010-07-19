package gherkin.formatter;

import gherkin.formatter.model.Range;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class LineFilter implements Filter {
    private final List<Long> lines;

    public LineFilter(List<Long> lines) {
        this.lines = lines;
    }

    public boolean eval(List<Tag> tags, List<String> names, List<Range> ranges) {
        for (Range range : ranges) {
            if (range != null) {
                for (Long line : lines) {
                    if (range.isInclude(line)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Row> filterTableBodyRows(List<Row> exampleRows) {
        List<Row> result = new ArrayList<Row>();
        for (Row row : exampleRows) {
            if (result.isEmpty()) {
                result.add(row);
            } else {
                for (Long line : lines) {
                    if (row.getLine() == line) {
                        result.add(row);
                    }
                }
            }
        }
        return result;
    }
}
