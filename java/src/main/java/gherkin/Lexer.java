package gherkin;

public interface Lexer {
    /**
     * Lexes the source.
     *
     * @param source a string containing Gherkin source.
     */
    public void scan(String source);
}
