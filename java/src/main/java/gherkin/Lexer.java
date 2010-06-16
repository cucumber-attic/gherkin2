package gherkin;

public interface Lexer {
    /**
     * Lexes the source.
     *
     * @param source a string containing Gherkin source.
     * @param uri the URI where the source originated from. Typically a file path.
     * @param offset the line offset within the uri document the source was taken from. Typically 0.
     */
    public void scan(String source, String uri, int offset);
}
