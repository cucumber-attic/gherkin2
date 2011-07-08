package gherkin.formatter;

import gherkin.formatter.model.*;

import java.util.List;

/**
 * Interface for reporting results. This is a different interface from Formatter,
 * which also knows how to print gherkin source. They are separate, because some
 * Reporter implementations (such as Cucumber's JUnitReporter) only cares about results,
 */
public interface Reporter {
    void result(Result result);
    void match(Match match);
    void embedding(String mimeType, byte[] data);
}
