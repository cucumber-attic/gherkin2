package gherkin.formatter;

import gherkin.formatter.model.Range;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Tag;

import java.util.List;
import java.util.regex.Pattern;

public class PatternFilter implements Filter {
    private final List<Pattern> patterns;

    public PatternFilter(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public boolean eval(List<Tag> tags, List<String> names, List<Range> ranges) {
        for (String name : names) {
            if (name != null) {
                for (Pattern pattern : patterns) {
                    if (pattern.matcher(name).find()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Row> filterTableBodyRows(List<Row> examplesRows) {
        return examplesRows;
    }
}
