package gherkin.formatter;

import gherkin.formatter.model.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrettyFormatterTest {
    @Test
    public void testShouldPrintNiceColors() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Scenario", "a scenario", "", 1));
        f.step(new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1));
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck", null));
        Thread.sleep(1000);
        f.result(new Result("failed", 55, "Something\nbad\nhappened"));
    }

}
