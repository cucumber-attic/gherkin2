package gherkin;

public class LexingError extends RuntimeException {
    public LexingError(String message) {
        super(message);
    }
}
