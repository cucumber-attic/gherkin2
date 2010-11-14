package gherkin.formatter;

import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Step;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class PrettyFormatterTest {
    @Test
    public void testShouldPrintNiceColors() throws UnsupportedEncodingException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1);
        f.steps(Arrays.asList(step));
        f.step(step);
    }

}
