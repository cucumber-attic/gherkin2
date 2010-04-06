package gherkin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class I18nLexer implements Lexer {
    private static final Pattern LANGUAGE_PATTERN = Pattern.compile("language\\s*:\\s*(.*)");
    private final Listener listener;
    private I18nLanguage i18nLanguage;

    public I18nLexer(Listener listener) {
        this.listener = listener;
    }

    /**
     * @return the i18n language from the previous scanned source.
     */
    public I18nLanguage getI18nLanguage() {
        return i18nLanguage;
    }

    public void scan(CharSequence source) {
        createDelegate(source).scan(source);
    }

    private Lexer createDelegate(CharSequence source) {
        i18nLanguage = lang(source);
        return i18nLanguage.lexer(listener);
    }

    private I18nLanguage lang(CharSequence source) {
        String lineOne = source.toString().split("\\n")[0];
        Matcher matcher = LANGUAGE_PATTERN.matcher(lineOne);
        String key = "en";
        if(matcher.find()) {
            key = matcher.group(1);
        }
        return new I18nLanguage(key);
    }
}
