package gherkin.formatter;

import org.json.simple.JSONValue;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MappableTest {
    public static class TestMappable extends Mappable {
        public final int an_int = 1;
        public final Long a_long = 2L;
        public final String a_string = "3";
        public final AnotherMappable a_mappable = new AnotherMappable();
        public final List<Short> a_short_list = asList((short) 4, (short) 5, (short) 6);
        public final Set<AnotherMappable> a_mappable_list = new TreeSet(asList(new AnotherMappable()));
        public final Object an_int_declared_as_object = 7;
        public final transient int a_transient_int = 1;

        // Non-mappable
        public final URL an_url;
        public final List<Class<String>> a_class_list = asList(String.class);

        public TestMappable() throws MalformedURLException {
            an_url = new URL("http://cukes.info/");
        }
    }

    public static class AnotherMappable extends Mappable {
        public final Integer another_int = 4;
        public final byte a_byte = 5;
        public final Class a_class = AnotherMappable.class;
    }

    @Test
    public void should_only_include_primitives_strings_mappables_and_collections_of_mappable() throws MalformedURLException {
        TestMappable tm = new TestMappable();
        Map<Object, Object> map = tm.toMap();

        String expected = "" +
                "{\n" +
                "  \"an_int\":1,\n" +
                "  \"a_long\":2,\n" +
                "  \"a_string\":\"3\",\n" +
                "  \"a_mappable\":{\n" +
                "    \"another_int\":4,\n" +
                "    \"a_byte\":5\n" +
                "  },\n" +
                "  \"a_short_list\":[\n" +
                "    4,\n" +
                "    5,\n" +
                "    6\n" +
                "  ],\n" +
                "  \"a_mappable_list\":[\n" +
                "    {\n" +
                "      \"another_int\":4,\n" +
                "      \"a_byte\":5\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        String actual = JSONValue.toJSONString(map);
        assertEquals(JSONValue.parse(expected), JSONValue.parse(actual));
    }

}
