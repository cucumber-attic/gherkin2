package gherkin.formatter;

import gherkin.formatter.model.HookResult;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;

import java.io.InputStream;

/**
 * Interface for reporting results. This is a different interface from Formatter,
 * which also knows how to print gherkin source. They are separate, because some
 * Reporter implementations (such as Cucumber's JUnitReporter) only cares about results,
 */
public interface Reporter {
    void result(Result result);

    /**
     * Included to allow reporting before hook results, usually only if errors
     * @param result
     */
    void before(HookResult result);

    /**
     * Included to report on after hook results, usually only if errors
     * @param result
     */
    void after(HookResult result);

    void match(Match match);

    void embedding(String mimeType, InputStream data);

    void write(String text);
}
