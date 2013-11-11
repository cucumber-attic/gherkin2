package gherkin.formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Tag;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class FilterFormatterTest {

    @Test
    public void filterFormatterShouldNotClearExampleTags() {

        FilterFormatter filterFormatter =  new FilterFormatter(mock(Formatter.class), Arrays.asList("@filter"));

        final ArrayList<Tag> originalTags = new ArrayList<Tag>();
        originalTags.add(new Tag("@tag", 0));

        Examples examples1 = new Examples(Collections.EMPTY_LIST, new ArrayList<Tag>(originalTags),
                "keyword1", "name1", "description1", 0, "id1", Collections.EMPTY_LIST);
        Examples examples2 = new Examples(Collections.EMPTY_LIST, new ArrayList<Tag>(originalTags),
                "keyword2", "name2", "description2", 0, "id2", Collections.EMPTY_LIST);

        filterFormatter.examples(examples1);
        filterFormatter.examples(examples2);

        assertEquals("Example tags should not be modified", originalTags, examples1.getTags());
        assertEquals("Example tags should not be modified", originalTags, examples2.getTags());
    }
}
