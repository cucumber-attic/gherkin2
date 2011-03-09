package gherkin.model;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class StepTest {
    @Test
    public void shouldProvideArgumentsForOutlineTokens() {
        Step step = new Step(Collections.<Comment>emptyList(), "Given ", "I have <number> cukes in <whose> belly", 10);
        assertEquals(7, step.getOutlineArgs().get(0).getOffset());
        assertEquals("<number>", step.getOutlineArgs().get(0).getVal());
        assertEquals(25, step.getOutlineArgs().get(1).getOffset());
        assertEquals("<whose>", step.getOutlineArgs().get(1).getVal());
    }
}
