package gherkin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class I18nLexer implements Lexer {
    private static final Pattern LANGUAGE_PATTERN = Pattern.compile("language\\s*:\\s*(.*)");
    private final Listener listener;
    private I18n i18n;

    public I18nLexer(Listener listener) {
        this(listener, false);
    }

    public I18nLexer(Listener listener, boolean ignored) {
        this.listener = listener;
    }

    /**
     * @return the i18n language from the previous scanned source.
     */
    public I18n getI18nLanguage() {
        return i18n;
    }

    public void scan(CharSequence source) {
        createDelegate(source).scan(source);
    }

    private Lexer createDelegate(CharSequence source) {
        i18n = i18nLanguage(source);
        return i18n.lexer(listener);
    }

    private I18n i18nLanguage(CharSequence source) {
        String lineOne = source.toString().split("\\n")[0];
        Matcher matcher = LANGUAGE_PATTERN.matcher(lineOne);
        String key = "en";
        if (matcher.find()) {
            key = matcher.group(1);
        }
        return new I18n(key);
    }
}
