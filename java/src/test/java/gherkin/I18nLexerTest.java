package gherkin;

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

        String feature = "Feature: Hello\n" +
                "  World\n" +
                "  Scenario Outline:\n" +
                "    Given I have an empty stack\n" +
                "    When I pøsh <x> onto the stack";

        lexer.scan(feature, "hello.feature", 0);

        verify(listener).feature("Feature", "Hello", "World", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
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

        lexer.scan(feature, "test.feature", 0);

        verify(listener).feature("Feature", "ÆØÅ", "", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }

    @Test
    public void shouldScanUtf8FeatureInFileProperly() throws UnsupportedEncodingException {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = FixJava.readResource("/gherkin/utf8.feature");

        lexer.scan(feature, "test.feature", 0);

        verify(listener).feature("Feature", "ÆØÅ", "", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }
}
