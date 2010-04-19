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
    public void shouldProvideKeywordsForChinese() throws IOException {
        I18n no = new I18n("zh-CN");
        assertEquals(Arrays.asList("* ", "但是"), no.keywords("but"));
    }

    @Test
    public void shouldProvideKeywordsForScouse() throws IOException {
        I18n no = new I18n("en-Scouse");
        assertEquals(Arrays.asList("* ", "Givun ", "Youse know when youse got "), no.keywords("given"));
    }
}
