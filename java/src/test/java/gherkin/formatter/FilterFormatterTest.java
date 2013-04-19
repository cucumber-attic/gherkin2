package gherkin.formatter;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FilterFormatterTest {
    @Test
    public void converts_legacy_tag() {
        String te = FilterFormatter.convertLegacyTagExpression(asList("@foo"));
        assertEquals("(@foo)", te);
    }

    @Test
    public void converts_legacy_tag_expression() {
        String te = FilterFormatter.convertLegacyTagExpression(asList("@foo,~@bar", "@zap"));
        assertEquals("(@foo||!@bar)&&(@zap)", te);
    }
}
