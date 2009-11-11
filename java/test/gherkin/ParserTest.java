package gherkin;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ParserTest {
    @Test
    public void shouldAllowTransiationFromBackgroundToStep() {
        Parser parser = new Parser(mock(Listener.class), true, "background");
        parser.step("Given", "a step", 3);
    }

    @Test(expected= ParseError.class)
    public void shouldNotAllowTransiationFromScenarioToBackground() {
        Parser parser = new Parser(mock(Listener.class), true, "scenario");
        parser.background("Background", "whatever", 3);
    }

}
