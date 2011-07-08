package gherkin.formatter.model;

import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Result extends Mappable {
    private final String status;
    private final long duration;
    private final String error_message;
    private final Throwable error;
    public static final Result SKIPPED = new Result("skipped", 0, (Throwable)null);
    public static final String PASSED = "passed";
    public static final String FAILED = "failed";

    /**
     * Used at runtime
     * @param status
     * @param duration
     * @param error
     */
    public Result(String status, long duration, Throwable error) {
        this.status = status;
        this.duration = duration;
        this.error_message = null;
        this.error = error;
    }

    /**
     * Used when parsing JSON. TODO: constructing an Exception instance when parsing JSON might be better.
     * @param status
     * @param duration
     * @param errorMessage
     */
    public Result(String status, long duration, String errorMessage) {
        this.status = status;
        this.duration = duration;
        this.error_message = errorMessage;
        this.error = null;
    }

    public String getStatus() {
        return status;
    }

    public long getDuration() {
        return duration;
    }

    public Throwable getError() {
        return error;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public void replay(Reporter reporter) {
        reporter.result(this);
    }
}
