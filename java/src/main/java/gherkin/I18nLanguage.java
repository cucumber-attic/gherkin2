package gherkin;

public class I18nLanguage {
    private final String key;

    public I18nLanguage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Lexer lexer(Listener listener) {
        String i18nLanguage = key.replaceAll("[\\s-]", "").toLowerCase();
        String i18nLexerClassName = i18nLanguage.substring(0,1).toUpperCase() + i18nLanguage.substring(1);
        String qualifiedI18nLexerClassName = "gherkin.lexer." + i18nLexerClassName;
        try {
            Class<?> delegateClass = Class.forName(qualifiedI18nLexerClassName);
            return (Lexer) delegateClass.getConstructor(Listener.class).newInstance(listener);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load lexer class: " + qualifiedI18nLexerClassName, e);
        }
    }
}
