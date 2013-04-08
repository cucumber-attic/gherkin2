package gherkin;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import gherkin.formatter.PrettyFormatter;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DataTableRow;
import gherkin.formatter.model.Row;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.util.Mapper;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

    private static final Mapper<String, String> QUOTE_MAPPER = new Mapper<String, String>() {
        @Override
        public String map(String o) {
            return '"' + o + '"';
        }
    };

    private static final Mapper<String, String> CODE_KEYWORD_MAPPER = new Mapper<String, String>() {
        @Override
        public String map(String keyword) {
            return codeKeywordFor(keyword);
        }
    };

    private static Map<String, Map<String, String>> I18N;

    static {
        try {
            I18N = new Gson().fromJson(new InputStreamReader(I18n.class.getResourceAsStream("/gherkin/i18n.json"), "UTF-8"), new TypeToken<Map<String, Map<String, String>>>() {
            }.getType());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String codeKeywordFor(String keyword) {
        return keyword.replaceAll("[\\s',!]", "");
    }

    public static List<I18n> getAll() {
        List<I18n> result = new ArrayList<I18n>();

        Set<String> isoCodes = new TreeSet<String>(I18N.keySet());
        for (String isoCode : isoCodes) {
            result.add(new I18n(isoCode));
        }
        return result;
    }

    private final String isoCode;
    private final Locale locale;
    private final Map<String, List<String>> keywords;

    public I18n(String isoCode) {
        this.isoCode = isoCode;
        this.locale = localeFor(this.isoCode);
        this.keywords = new HashMap<String, List<String>>();

        Map<String, String> keywordMap = I18N.get(isoCode);
        for (Map.Entry<String, String> entry : keywordMap.entrySet()) {
            List<String> keywordList = Arrays.asList(entry.getValue().split("\\|"));
            if (STEP_KEYWORD_KEYS.contains(entry.getKey())) {
                List<String> stepKeywords = new ArrayList<String>();
                for (String s : keywordList) {
                    stepKeywords.add((s + " ").replaceFirst("< $", ""));
                }
                keywordList = stepKeywords;
            }
            this.keywords.put(entry.getKey(), Collections.unmodifiableList(keywordList));
        }
    }

    public String getIsoCode() {
        return isoCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getUnderscoredIsoCode() {
        return getIsoCode().replaceAll("[\\s-]", "_").toLowerCase();
    }

    public Lexer lexer(Listener listener) {
        String qualifiedI18nLexerClassName = "gherkin.lexer." + capitalize(getUnderscoredIsoCode());
        try {
            Class<?> delegateClass = getClass().getClassLoader().loadClass(qualifiedI18nLexerClassName);
            return (Lexer) delegateClass.getConstructor(Listener.class).newInstance(listener);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load lexer class: " + qualifiedI18nLexerClassName, e);
        }
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public List<String> keywords(String key) {
        if (!keywords.containsKey(key)) {
            throw new RuntimeException("No such key: " + key + ". Available keys: " + keywords.keySet());
        }
        return keywords.get(key);
    }

    public List<String> getCodeKeywords() {
        List<String> result = map(getStepKeywords(), CODE_KEYWORD_MAPPER);
        result.remove("*");
        ListIterator<String> it = result.listIterator();
        while (it.hasNext()) {
            if (it.next().matches("^\\d.*")) {
                it.remove();
            }
        }
        return result;
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
        PrettyFormatter pf = new PrettyFormatter(writer, true, false);
        List<Row> table = new ArrayList<Row>();
        for (String key : KEYWORD_KEYS) {
            List<String> cells = Arrays.asList(key, join(map(keywords(key), QUOTE_MAPPER), ", "));
            table.add(new DataTableRow(Collections.<Comment>emptyList(), cells, -1));
        }
        for (String key : STEP_KEYWORD_KEYS) {
            List<String> codeKeywordList = new ArrayList<String>(keywords.get(key));
            codeKeywordList.remove("* ");
            String codeKeywords = join(map(map(codeKeywordList, CODE_KEYWORD_MAPPER), QUOTE_MAPPER), ", ");

            List<String> cells = Arrays.asList(key + " (code)", codeKeywords);
            table.add(new DataTableRow(Collections.<Comment>emptyList(), cells, -1));
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
