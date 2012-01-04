package gherkin;

import gherkin.util.Mapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static gherkin.util.FixJava.map;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

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
        assertEquals(asList("ar,bg,ca,cs,cy-GB,da,de,en,en-Scouse,en-au,en-lol,en-pirate,en-tx,eo,es,et,fi,fr,he,hr,hu,id,is,it,ja,ko,lt,lu,lv,nl,no,pl,pt,ro,ru,sk,sr-Cyrl,sr-Latn,sv,tr,uk,uz,vi,zh-CN,zh-TW".split(",")), isoCodes);
    }


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
