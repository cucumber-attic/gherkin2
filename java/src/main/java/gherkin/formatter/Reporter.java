package gherkin.formatter;

import gherkin.model.*;

public interface Reporter{
    void match(Match match);
    void embedding(String mimeType, byte[] data);
    void result(Result result);
}
