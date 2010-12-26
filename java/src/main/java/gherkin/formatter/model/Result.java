package gherkin.formatter.model;

import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

public class Result extends Mappable {
    private final String status;
    private final long duration;
    private final String error_message;
    public static final Result SKIPPED = new Result("skipped", 0, null);
    public static final Result UNDEFINED = new Result("undefined", 0, null);


    public Result(String status, long duration, String errorMessage) {
        this.status = status;
        this.duration = duration;
        this.error_message = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public long getDuration() {
        return duration;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public void replay(Reporter reporter) {
        reporter.result(this);
    }
}
