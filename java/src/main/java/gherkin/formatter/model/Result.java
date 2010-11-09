package gherkin.formatter.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Mappable;

import java.util.List;

public class Result extends Mappable {
    private final String status;
    private final String error_message;
    private final List<Argument> arguments;
    private final String stepdef_location;

    public Result(String status, String errorMessage, List<Argument> arguments, String stepdefLocation) {
        this.status = status;
        this.error_message = errorMessage;
        this.arguments = arguments;
        this.stepdef_location = stepdefLocation;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getStepdefLocation() {
        return stepdef_location;
    }
}
