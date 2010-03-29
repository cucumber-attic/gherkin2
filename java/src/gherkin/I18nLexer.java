package gherkin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class I18nLexer implements Lexer {
    private static final Pattern LANGUAGE_PATTERN = Pattern.compile("language\\s*:\\s*(.*)");
    private final Listener listener;
    private String i18nLanguage;

    public I18nLexer(Listener listener) {
        this.listener = listener;
    }

    /**
     * @return the i18n language code from the previous scanned source.
     */
    public String getI18nLanguage() {
        return i18nLanguage;
    }

    public void scan(CharSequence source) {
        createDelegate(source).scan(source);
    }

    private Lexer createDelegate(CharSequence source) {
        i18nLanguage = lang(source);
        String i18nLanguage = this.i18nLanguage.replaceAll("[\\s-]", "").toLowerCase();
        String i18nLexerClassName = i18nLanguage.substring(0,1).toUpperCase() + i18nLanguage.substring(1);
        String qualifiedI18nLexerClassName = "gherkin.lexer." + i18nLexerClassName;
        try {
            Class<?> delegateClass = Class.forName(qualifiedI18nLexerClassName);
            return (Lexer) delegateClass.getConstructor(Listener.class).newInstance(listener);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load lexer class: " + qualifiedI18nLexerClassName, e);
        }
    }

    private String lang(CharSequence source) {
        String lineOne = source.toString().split("\\n")[0];
        Matcher matcher = LANGUAGE_PATTERN.matcher(lineOne);
        if(matcher.find()) {
            return matcher.group(1);
        } else {
            return "en";            
        }
    }
}
