package gherkin.formatter;

import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Step;

import java.util.List;

public interface Reporter extends Formatter {
    void steps(List<Step> steps);
    void result(Result result);
    void match(Match match);
    void embedding(String mimeType, byte[] data);
    void table(List<Row> rows);
    void row(List<String> cellFormats);
}
