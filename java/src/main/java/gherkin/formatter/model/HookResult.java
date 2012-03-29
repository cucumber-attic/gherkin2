package gherkin.formatter.model;

public class HookResult extends Result {
    private final String location;

    public HookResult(String location, String status, Long duration, Throwable error, Object dummy) {
        super(status, duration, error, dummy);
        this.location = location;
    }

    public HookResult(String location, String status, Long duration, String errorMessage) {
        super(status, duration, errorMessage);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
