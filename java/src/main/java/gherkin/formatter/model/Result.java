package gherkin.formatter.model;

import gherkin.formatter.Formatter;
import gherkin.formatter.Mappable;

public class Result extends Mappable {
    private final String status;
    private final String error_message;

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

    public void replay(Formatter formatter) {
        formatter.result(this);
    }
}
