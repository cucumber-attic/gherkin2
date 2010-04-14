package gherkin.parser;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TagExpressionTest {
    @Test
    public void notFooShouldMatchBar() {
        TagExpression e = new TagExpression(Collections.singletonList("~@foo"));
        assertTrue(e.eval(Collections.singletonList("@bar")));
    }

    @Test
    public void notFooShouldNotMatchFoo() {
        TagExpression e = new TagExpression(Collections.singletonList("~@foo"));
        assertFalse(e.eval(Collections.singletonList("@foo")));
    }

    @Test
    public void fooShouldNotMatchEmptyTags() {
        TagExpression e = new TagExpression(Collections.singletonList("@foo"));
        assertFalse(e.eval(Collections.<String>emptyList()));
    }
}
