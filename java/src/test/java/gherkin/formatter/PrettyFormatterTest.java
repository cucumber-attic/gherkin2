package gherkin.formatter;

import gherkin.formatter.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PrettyFormatterTest {
    private static final List<Comment> NO_COMMENTS = emptyList();
    private static final List<Tag> NO_TAGS = Collections.emptyList();

    @Test
    public void prints_nice_colors() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, NO_TAGS, "Scenario", "a scenario", "", 1, "a-scenario"));
        f.step(new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1, null, null));
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }

    @Test
    public void prints_table() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, Collections.<Tag>emptyList(), "Scenario", "a scenario", "", 1, "a-scenario"));
        ArrayList<DataTableRow> rows = new ArrayList<DataTableRow>() {{
            add(new DataTableRow(NO_COMMENTS, asList("un", "deux"), 1, Row.DiffType.NONE));
            add(new DataTableRow(NO_COMMENTS, asList("one", "two"), 1, Row.DiffType.DELETE));
            add(new DataTableRow(NO_COMMENTS, asList("en", "to"), 1, Row.DiffType.INSERT));
        }};
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1, rows, null);
        f.step(step);
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }
    
    @Test
    public void shouldNotCloseProvidedStreamInDone() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new PrettyFormatter(out, true, true);
        formatter.done();
        verify(out, never()).close();
    }

    @Test
    public void shouldFlushAndCloseProvidedStreamInClose() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new PrettyFormatter(out, true, true);
        formatter.close();
        verify(out).flush();
        verify(out).close();
    }

    /**
     * Tests the indentation logic in {@link PrettyFormatter} accepts 0-argument steps.
     * If the underlying {@link Match} instance passed to {@link PrettyFormatter}
     * doesn't have a "statement", it doesn't initialize the "indentations" array.
     * Afterwards, "indentedLocation(location, proceed)" method is called, which
     * assumes the "indentations" array is not empty, which doesn't hold in this
     * case.
     */
    @Test
    public void shouldAlwaysCalculateIndentations() {
        PrintStream out = mock(PrintStream.class);
        PrettyFormatter formatter = new PrettyFormatter(out, false, true);
        Match match = new Match(Collections.<Argument>emptyList(), "/fake/location");

        try {
            formatter.match(match);
        } catch (final ArrayIndexOutOfBoundsException indentedLocationsBug) {
            Assert.fail("bug in indentation of 0-argument steps");
        } catch (final IndexOutOfBoundsException indentedLocationsBug) {
            Assert.fail("bug in indentation of 0-argument steps");
        } finally {
            formatter.close();
            verify(out).flush();
            verify(out).close();
        }
    }
}
