package gherkin;

import gherkin.lexer.I18nLexer;
import gherkin.lexer.Lexer;
import gherkin.util.FixJava;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class I18nLexerTest {
    @Test
    public void shouldScanMultiLineFeature() {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = "" +
                " Feature: Hello\n" +
                "     Big    \n" +
                "       World  \n" +
                "  Scenario Outline:\n" +
                "    Given I have an empty stack\n" +
                "    When I pøsh <x> onto the stack";

        lexer.scan(feature);

        verify(listener).feature("Feature", "Hello", "  Big    \n    World", 1);
        verify(listener).step("Given ", "I have an empty stack", 5);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 6);
    }

    @Test
    public void shouldScanUtf8FeatureInSourceProperly() {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = "Feature: ÆØÅ\n" +
                "\n" +
                "  Scenario Outline:\n" +
                "    Given I have an empty stack\n" +
                "    When I pøsh <x> onto the stack";

        lexer.scan(feature);

        verify(listener).feature("Feature", "ÆØÅ", "", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }

    @Test
    public void shouldScanUtf8FeatureInFileProperly() throws UnsupportedEncodingException {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = FixJava.readResource("/gherkin/utf8.feature");

        lexer.scan(feature);

        verify(listener).feature("Feature", "ÆØÅ", "", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }
}
