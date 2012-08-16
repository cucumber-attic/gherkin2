package gherkin.formatter.model;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class StepTest {
    @Test
    public void shouldProvideArgumentsForOutlineTokens() {
        Step step = new Step(Collections.<Comment>emptyList(), "Given ", "I have <number> cukes in <whose> belly", 10, null, null);
        assertEquals(7, step.getOutlineArgs().get(0).getOffset().intValue());
        assertEquals("<number>", step.getOutlineArgs().get(0).getVal());
        assertEquals(25, step.getOutlineArgs().get(1).getOffset().intValue());
        assertEquals("<whose>", step.getOutlineArgs().get(1).getVal());
    }
}
