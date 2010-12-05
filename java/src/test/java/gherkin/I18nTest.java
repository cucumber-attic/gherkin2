package gherkin;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class I18nTest {
    @Test
    public void shouldProvideKeywordsForNorwegian() throws IOException {
        I18n no = new I18n("no");
        assertEquals(Arrays.asList("* ", "Så "), no.keywords("then"));
    }

    @Test
    public void shouldProvideKeywordsForIndonesian() throws IOException {
        I18n no = new I18n("id");
        assertEquals(Arrays.asList("* ", "Ketika "), no.keywords("when"));
    }

    @Test
    public void shouldProvideKeywordsForHebrew() throws IOException {
        I18n he = new I18n("he");
        assertEquals(Arrays.asList("דוגמאות"), he.keywords("examples"));
    }

    @Test
    public void shouldProvideKeywordsForChinese() throws IOException {
        I18n zhCn = new I18n("zh-CN");
        assertEquals(Arrays.asList("* ", "但是"), zhCn.keywords("but"));
    }

    @Test
    public void shouldProvideKeywordsForScouse() throws IOException {
        I18n enScouse = new I18n("en-Scouse");
        assertEquals(Arrays.asList("* ", "Givun ", "Youse know when youse got "), enScouse.keywords("given"));
    }
}
