package gherkin;

public interface Lexer {
    public void scan(String gherkinSource, String uri);
}
