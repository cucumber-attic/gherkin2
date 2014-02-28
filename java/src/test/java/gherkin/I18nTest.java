package gherkin;

import gherkin.deps.com.google.gson.reflect.TypeToken;
import gherkin.deps.com.google.gson.Gson;
import gherkin.util.Mapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static gherkin.util.FixJava.map;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class I18nTest {
    @Test
    public void shouldFindAllLanguages() throws IOException {
        List<I18n> all = I18n.getAll();
        List<String> isoCodes = map(all, new Mapper<I18n, String>() {
            @Override
            public String map(I18n i18n) {
                return i18n.getIsoCode();
            }
        });
        Map<String, Map<String, String>> i18nContent =
                new Gson().fromJson(new InputStreamReader(I18n.class.getResourceAsStream("/gherkin/i18n.json"), "UTF-8"), new TypeToken<Map<String, Map<String, String>>>() {}.getType());
        List<String> i18nContentKeys = extractSortedListOfMapKeys(i18nContent);

        assertEquals(i18nContentKeys, isoCodes);
        assertThat(isoCodes, hasItem("ar"));
        assertThat(isoCodes, hasItem("en"));
        assertThat(isoCodes, hasItem("zh-TW"));
    }

    @Test
    public void shouldProvideKeywordsForNorwegian() throws IOException {
        I18n no = new I18n("no");
        assertEquals(Arrays.asList("* ", "Så "), no.keywords("then"));
        assertEquals("no", no.getLocale().getLanguage());
    }

    @Test
    public void shouldProvideKeywordsForIndonesian() throws IOException {
        I18n id = new I18n("id");
        assertEquals(Arrays.asList("* ", "Ketika "), id.keywords("when"));
        assertEquals("in", id.getLocale().getLanguage());
    }

    @Test
    public void shouldProvideKeywordsForHebrew() throws IOException {
        I18n he = new I18n("he");
        assertEquals(Arrays.asList("דוגמאות"), he.keywords("examples"));
        assertEquals("iw", he.getLocale().getLanguage());
    }

    @Test
    public void shouldProvideKeywordsForChinese() throws IOException {
        I18n zhCn = new I18n("zh-CN");
        assertEquals(Arrays.asList("* ", "但是"), zhCn.keywords("but"));
        assertEquals("zh", zhCn.getLocale().getLanguage());
    }

    @Test
    public void shouldProvideKeywordsForScouse() throws IOException {
        I18n enScouse = new I18n("en-Scouse");
        assertEquals(Arrays.asList("* ", "Givun ", "Youse know when youse got "), enScouse.keywords("given"));
        assertEquals("en", enScouse.getLocale().getLanguage());
    }

    @Test
    public void shouldProvideKeywordsForKlingon() throws IOException {
        I18n tlh = new I18n("tlh");
        assertEquals(Arrays.asList("* ", "ghu' noblu' ", "DaH ghu' bejlu' "), tlh.keywords("given"));
        assertEquals("tlh", tlh.getLocale().getLanguage());
    }

    private List<String> extractSortedListOfMapKeys(Map<String, ?> map) {
        String[] emptyStringArray = {};
        List<String> keys = asList(map.keySet().toArray(emptyStringArray));
        Collections.sort(keys);
        return keys;
    }
}
