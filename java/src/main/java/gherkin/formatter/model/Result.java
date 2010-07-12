package gherkin.formatter.model;

import gherkin.formatter.Argument;

import java.util.List;

public class Result {
    private final String status;
    private final String errorMessage;
    private final List<Argument> arguments;
    private final String stepdefLocation;

    public Result(String status, String errorMessage, List<Argument> arguments, String stepdefLocation) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.arguments = arguments;
        this.stepdefLocation = stepdefLocation;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getStepdefLocation() {
        return stepdefLocation;
    }
}
