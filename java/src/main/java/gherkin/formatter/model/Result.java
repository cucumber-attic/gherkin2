package gherkin.formatter.model;

import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

public class Result extends Mappable {
    private final String status;
    private final String error_message;
    public static final Result SKIPPED = new Result("skipped", null);
    public static final Result UNDEFINED = new Result("undefined", null);


    public Result(String status, String errorMessage) {
        this.status = status;
        this.error_message = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public void replay(Reporter reporter) {
        reporter.result(this);
    }
}
