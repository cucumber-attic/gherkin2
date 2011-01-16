package gherkin.formatter;

import gherkin.formatter.model.*;

import java.util.List;

public interface Reporter extends Formatter {
    void match(Match match);
    void embedding(String mimeType, byte[] data);
    void result(Result result);
}
