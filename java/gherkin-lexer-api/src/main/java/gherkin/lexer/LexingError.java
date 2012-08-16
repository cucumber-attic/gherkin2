package gherkin.lexer;

public class LexingError extends RuntimeException {
	private static final long serialVersionUID = 5533217655647222101L;
	public LexingError(String message) {
        super(message);
    }
}
