package gherkin.lexer;

import gherkin.I18n;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class I18nLexer implements Lexer {
    private static final Pattern COMMENT_OR_EMPTY_LINE_PATTERN = Pattern.compile("^([\\W]+)?\\s*#|^\\s*$");
    private static final Pattern LANGUAGE_PATTERN = Pattern.compile("^([\\W]+)?\\s*#\\s*language\\s*:\\s*([a-zA-Z\\-]+)");
    private final Listener listener;
    private I18n i18n;
    private String isoCode;

    public I18nLexer(Listener listener) {
        this(listener, false, "en");
    }

    public I18nLexer(Listener listener, boolean forceRubyDummy) {
        this(listener, forceRubyDummy, "en");
    }

    public I18nLexer(Listener listener, boolean forceRubyDummy, String isoCode) {
        this.listener = listener;
        this.isoCode = isoCode;
    }

    /**
     * @return the i18n language from the previous scanned source.
     */
    public I18n getI18nLanguage() {
        return i18n;
    }

    public void scan(String source) {
        createDelegate(source).scan(source);
    }

    private Lexer createDelegate(String source) {
        i18n = i18nLanguageForSource(source);
        return i18n.lexer(listener);
    }

    private I18n i18nLanguageForSource(String source) {
        String key = isoCode;
        for (String line : source.split("\\n")) {
            if (!COMMENT_OR_EMPTY_LINE_PATTERN.matcher(line).find()) {
                break;
            }
            Matcher matcher = LANGUAGE_PATTERN.matcher(line);
            if (matcher.find()) {
                key = matcher.group(2);
                break;
            }
        }
        return new I18n(key);
    }
}
