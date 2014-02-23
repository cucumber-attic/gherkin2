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
        assertEquals(asList("af,ar,bg,bm,ca,cs,cy-GB,da,de,el,en,en-Scouse,en-au,en-lol,en-old,en-pirate,en-tx,eo,es,et,fa,fi,fr,gl,he,hi,hr,hu,id,is,it,ja,kn,ko,lt,lu,lv,nl,no,pa,pl,pt,ro,ru,sk,sl,sr-Cyrl,sr-Latn,sv,th,tl,tlh,tr,tt,uk,uz,vi,zh-CN,zh-TW".split(",")), isoCodes);
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
}
