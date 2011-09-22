package gherkin.formatter;

import gherkin.formatter.model.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class PrettyFormatterTest {
    private static final List<Comment> NO_COMMENTS = emptyList();
    private static final List<Tag> NO_TAGS = Collections.<Tag>emptyList();

    @Test
    public void prints_nice_colors() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, NO_TAGS, "Scenario", "a scenario", "", 1));
        f.step(new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1));
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }

    @Test
    public void prints_table() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, Collections.<Tag>emptyList(), "Scenario", "a scenario", "", 1));
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1);
        step.setRows(new ArrayList<Row>() {{
            add(new Row(NO_COMMENTS, asList("un", "deux"), 1, Row.DiffType.NONE));
            add(new Row(NO_COMMENTS, asList("one", "two"), 1, Row.DiffType.DELETE));
            add(new Row(NO_COMMENTS, asList("en", "to"), 1, Row.DiffType.INSERT));
        }});
        f.step(step);
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }
}
