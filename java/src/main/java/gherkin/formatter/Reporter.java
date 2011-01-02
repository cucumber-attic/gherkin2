package gherkin.formatter;

import gherkin.formatter.model.*;

import java.util.List;

public interface Reporter extends Formatter {
    void result(Result result);
    void match(Match match);
    void embedding(String mimeType, byte[] data);
    void table(List<Row> rows);
    void row(List<CellResult> cellResults);
    void nextRow();
}
