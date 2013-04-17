package gherkin.formatter;

import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class LineFilter implements Filter {
    private final List<Integer> lines;

    public LineFilter(List<Integer> lines) {
        // During our tests, lines is passed in from Ruby, and it's a list of Long.
        this.lines = toIntegers(lines);
    }

    private List<Integer> toIntegers(List<?> lines) {
        List<Integer> result = new ArrayList<Integer>(lines.size());
        for (Object line : lines) {
            if (line instanceof Number) {
                result.add(((Number) line).intValue());
            } else {
                throw new IllegalArgumentException("Not a list of numbers: " + lines);
            }
        }
        return result;
    }

    public boolean evaluate(List<Tag> tags, List<String> names, List<Range> ranges) {
        for (Range range : ranges) {
            if (range != null) {
                for (Integer line : lines) {
                    if (range.isInclude(line)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<ExamplesTableRow> filterTableBodyRows(List<ExamplesTableRow> exampleRows) {
        List<ExamplesTableRow> result = new ArrayList<ExamplesTableRow>();
        for (ExamplesTableRow row : exampleRows) {
            if (result.isEmpty()) {
                result.add(row);
            } else {
                for (Integer line : lines) {
                    if (row.getLine().equals(line)) {
                        result.add(row);
                    }
                }
            }
        }
        return result;
    }
}
