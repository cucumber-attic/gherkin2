package gherkin.formatter.model;

import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Result extends Mappable {
    private static final long serialVersionUID = 1L;

    private final String status;
    private final Long duration;
    private final String error_message;
    private final Throwable error;
    public static final Result SKIPPED = new Result("skipped", null, null);
    public static final Result UNDEFINED = new Result("undefined", null, null);
    public static final String PASSED = "passed";
    public static final String FAILED = "failed";

    /**
     * Used at runtime
     *
     * @param status
     * @param duration
     * @param error
     * @param dummy    only used to distinguish the constructor when used from JRuby (and null for error).
     */
    public Result(String status, Long duration, Throwable error, Object dummy) {
        this.status = status;
        this.duration = duration;
        this.error_message = error != null ? getErrorMessage(error) : null;
        this.error = error;
    }

    /**
     * Used when parsing JSON. TODO: constructing an Exception instance when parsing JSON might be better.
     *
     * @param status
     * @param duration
     * @param errorMessage
     */
    public Result(String status, Long duration, String errorMessage) {
        this.status = status;
        this.duration = duration;
        this.error_message = errorMessage;
        this.error = null;
    }

    public String getStatus() {
        return status;
    }

    public Long getDuration() {
        return duration;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public Throwable getError() {
        return error;
    }

    private String getErrorMessage(Throwable error) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        error.printStackTrace(printWriter);
        return stringWriter.getBuffer().toString();
    }

    public void replay(Reporter reporter) {
        reporter.result(this);
    }
}
