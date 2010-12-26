package gherkin.formatter;

import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Step;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class PrettyFormatterTest {
    @Test
    public void testShouldPrintNiceColors() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1);
        f.steps(Arrays.asList(step));
        f.step(step);
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55, "Something\nbad\nhappened"));
    }

}
