package gherkin;

import gherkin.formatter.model.Tag;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TagExpressionTest {
    @Test
    public void notFooShouldMatchBar() {
        TagExpression e = new TagExpression(Collections.singletonList("~@foo"));
        assertTrue(e.evaluate(Collections.singletonList(new Tag("@bar", 1))));
    }

    @Test
    public void notFooShouldNotMatchFoo() {
        TagExpression e = new TagExpression(Collections.singletonList("~@foo"));
        assertFalse(e.evaluate(Collections.singletonList(new Tag("@foo", 1))));
    }

    @Test
    public void fooShouldNotMatchEmptyTags() {
        TagExpression e = new TagExpression(Collections.singletonList("@foo"));
        assertFalse(e.evaluate(Collections.<Tag>emptyList()));
    }
}
