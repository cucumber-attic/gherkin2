package gherkin.formatter;

import gherkin.formatter.model.Comment;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Step;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class PrettyFormatterTest {
    @Test
    public void testShouldPrintNiceColors() throws UnsupportedEncodingException {
        PrettyFormatter f = new PrettyFormatter(System.out, false);
        Argument arg = new Argument(7, "6");
        Result result = new Result("passed", null, Arrays.asList(arg), "somewhere");
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1, null, result);
        f.steps(Arrays.asList(step));
        f.step(step);
    }

}
