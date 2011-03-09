package gherkin.model;

import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Result extends Mappable {
    private final String status;
    private final long duration;
    private final Throwable error;
    private final String errorMessage;
    
    public static final Result SKIPPED = new Result("skipped", 0, null, null);
    public static final String PASSED = "passed";
    public static final String FAILED = "failed";

    public Result(String status, long duration) {
        this(status, duration, null, null);
    }

    public Result(String status, long duration, Throwable error) {
        this(status, duration, error, createErrorMessage(error));
    }

    public Result(String status, long duration, String errorMessage) {
        this(status, duration, null, errorMessage);
    }

    private Result(String status, long duration, Throwable error, String errorMessage) {
        this.status = status;
        this.duration = duration;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    /**
     * @return duration in nanoseconds.
     */
    public long getDuration() {
        return duration;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Throwable getError() {
        return error;
    }

    public void replay(Reporter reporter) {
        reporter.result(this);
    }

    private static String createErrorMessage(Throwable error) {
        if (error == null) return null;
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        error.printStackTrace(pw);
        return sw.toString();
    }
}
