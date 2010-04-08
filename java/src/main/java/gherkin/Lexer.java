package gherkin;

public interface Lexer {
    public void scan(CharSequence inputSequence) throws Exception;
}
