package gherkin;

import gherkin.formatter.PrettyFormatter;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Row;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.util.Mapper;

import java.io.StringWriter;
import java.util.*;

import static gherkin.util.FixJava.join;
import static gherkin.util.FixJava.map;

public class I18n {
    private static final List<String> FEATURE_ELEMENT_KEYWORD_KEYS = Arrays.asList("feature", "background", "scenario", "scenario_outline", "examples");
    private static final List<String> STEP_KEYWORD_KEYS = Arrays.asList("given", "when", "then", "and", "but");
    private static final List<String> KEYWORD_KEYS = new ArrayList<String>();

    static {
        KEYWORD_KEYS.addAll(FEATURE_ELEMENT_KEYWORD_KEYS);
        KEYWORD_KEYS.addAll(STEP_KEYWORD_KEYS);
    }

    private static final Mapper QUOTE_MAPPER = new Mapper() {
        public String map(Object o) {
            return '"' + (String) o + '"';
        }
    };

    private static final Mapper CODE_KEYWORD_MAPPER = new Mapper() {
        public String map(Object keyword) {
            return codeKeywordFor((String) keyword);
        }
    };

    private static String codeKeywordFor(String keyword) {
        return keyword.replaceAll("[\\s',]", "");
    }

    private final String isoCode;
    private final Locale locale;
    private final Map<String, List<String>> keywords;

    public I18n(String isoCode) {
        this.isoCode = isoCode;
        this.locale = localeFor(this.isoCode);
        this.keywords = new HashMap<String, List<String>>();

        populateKeywords();
    }

    private void populateKeywords() {
        ResourceBundle keywordBundle = ResourceBundle.getBundle("gherkin.I18nKeywords", locale);
        Enumeration<String> keys = keywordBundle.getKeys();
        while (keys.hasMoreElements()) {
            List<String> keywordList = new ArrayList<String>();
            String key = keys.nextElement();

            String value = keywordBundle.getString(key);
            keywordList.addAll(Arrays.asList(value.split("\\|")));
            keywords.put(key, Collections.unmodifiableList(keywordList));
        }
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getUnderscoredIsoCode() {
        return getIsoCode().replaceAll("[\\s-]", "_").toLowerCase();
    }

    public Lexer lexer(Listener listener) {
        String qualifiedI18nLexerClassName = "gherkin.lexer.i18n." + localeName().toUpperCase();
        try {
            Class<?> delegateClass = getClass().getClassLoader().loadClass(qualifiedI18nLexerClassName);
            return (Lexer) delegateClass.getConstructor(Listener.class).newInstance(listener);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load lexer class: " + qualifiedI18nLexerClassName, e);
        }
    }

    /**
     * Workaround for he and id bugs in the JDK.
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6457127
     * http://forums.sun.com/thread.jspa?threadID=5335461
     *
     * @return locale name.
     */
    private String localeName() {
        return locale.toString().replaceAll("^iw", "he").replaceAll("^in", "id");
    }

    public List<String> keywords(String key) {
        if (!keywords.containsKey(key)) {
            throw new RuntimeException("No such key: " + key + ". Available keys: " + keywords.keySet());
        }
        return keywords.get(key);
    }

    public List<String> getCodeKeywords() {
        return map(getStepKeywords(), CODE_KEYWORD_MAPPER);
    }

    public List<String> getStepKeywords() {
        SortedSet<String> result = new TreeSet<String>();
        for (String keyword : STEP_KEYWORD_KEYS) {
            result.addAll(keywords.get(keyword));
        }
        return new ArrayList<String>(result);
    }

    public String getKeywordTable() {
        StringWriter writer = new StringWriter();
        PrettyFormatter pf = new PrettyFormatter(writer, true);
        List<Row> table = new ArrayList<Row>();
        for (String key : KEYWORD_KEYS) {
            List<String> cells = Arrays.asList(key, join(map(keywords(key), QUOTE_MAPPER), ", "));
            table.add(new Row(Collections.<Comment>emptyList(), cells, -1));
        }
        for (String key : STEP_KEYWORD_KEYS) {
            List<String> codeKeywordList = new ArrayList<String>(keywords.get(key));
            codeKeywordList.remove("* ");
            String codeKeywords = join(map(map(codeKeywordList, CODE_KEYWORD_MAPPER), QUOTE_MAPPER), ", ");

            List<String> cells = Arrays.asList(key + " (code)", codeKeywords);
            table.add(new Row(Collections.<Comment>emptyList(), cells, -1));
        }
        pf.table(table);
        return writer.getBuffer().toString();
    }

    private Locale localeFor(String isoString) {
        String[] languageAndCountry = isoString.split("-");
        if (languageAndCountry.length == 1) {
            return new Locale(isoString);
        } else {
            return new Locale(languageAndCountry[0], languageAndCountry[1]);
        }
    }
}
