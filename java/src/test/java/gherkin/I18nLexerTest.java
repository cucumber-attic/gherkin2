package gherkin;

import org.junit.Test;

import java.io.InputStreamReader;

import static org.mockito.Mockito.*;

public class I18nLexerTest {
    @Test
    public void shouldScanUtf8FeatureInSourceProperly() {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = "Feature: ÆØÅ\n" +
                "\n" +
                "  Scenario Outline:\n" +
                "    Given I have an empty stack\n" +
                "    When I pøsh <x> onto the stack";

        System.out.println("SCANNING SOURCE STRING:");
        System.out.println(feature);
        lexer.scan(feature);

        verify(listener).feature("Feature", "ÆØÅ", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }

    @Test
    public void shouldScanUtf8FeatureInFileProperly() {
        Listener listener = mock(Listener.class);
        Lexer lexer = new I18nLexer(listener);

        String feature = FixJava.readReader(new InputStreamReader(getClass().getResourceAsStream("/gherkin/utf8.feature")));

        System.out.println("SCANNING FILE RESOURCE:");
        System.out.println(feature);
        lexer.scan(feature);

        verify(listener).feature("Feature", "ÆØÅ", 1);
        verify(listener).step("When ", "I pøsh <x> onto the stack", 5);
    }
}
