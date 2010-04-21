package gherkin;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class I18nLexerTest {
    @Test
    public void shouldScanUtf8FeaturesProperly() {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = "Feature: ÆØÅ\n" +
                "\n" +
                "  Scenario Outline:\n" +
                "    Given I have an empty stack\n" +
                "    When I pøsh <x> onto the stack";

        lexer.scan(feature);

        verify(listener).feature("Feature", "ÆØÅ", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }
}
