package gherkin.parser;

import gherkin.I18nLexer;
import gherkin.Lexer;
import gherkin.Listener;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Just a couple of translated RSpec specs, making it easier to fix problems in an IDE.
 */
public class FilterListenerTest {
    private String input;
    private List<Object[]> argumentList = new ArrayList<Object[]>();

    private class LineListenerInvocationHandler implements InvocationHandler {
        public Object invoke(Object target, Method method, Object[] arguments) {
            if (!method.getName().equals("location")) {
                argumentList.add(arguments);
            }
            return null;
        }
    }

    @Test
    public void shouldReplayIdenticallyWhenLineFilterIsFeatureLine() {
        input = "Feature: 1\n" +
                "  Scenario: 2\n" +
                "    Given 3\n" +
                "\n" +
                "  Scenario: 5\n" +
                "    Given 6" +
                "";
        verifyFilters(Arrays.asList(1, 2, 3, 5, 6, -1), Arrays.asList(1));
    }


    @Test
    public void scenarioOutlineShouldMatchExamplesNameOfSecondScenarioOutline() {
        input = "Feature: 1\n" +
                "\n" +
                "  @tag3\n" +
                "  Scenario Outline: 4\n" +
                "    Given <foo> 5\n" +
                "    When <bar> 6\n" +
                "\n" +
                "    @tag8\n" +
                "    Examples: 9\n" +
                "      | foo | bar |\n" +
                "      | 11  | 11  |\n" +
                "      | 12  | 12  |\n" +
                "      | 13  | 13  |\n" +
                "      | 14  | 14  |\n" +
                "      | 15  | 15  |\n" +
                "\n" +
                "    @tag17\n" +
                "    Examples: 18\n" +
                "      | snip | snap |\n" +
                "      | 20   | 20   |\n" +
                "      | 21   | 21   |\n" +
                "\n" +
                "  Scenario: 23\n" +
                "    Given 24\n" +
                "    When 25" +
                "";
        verifyFilters(Arrays.asList(1, 3, 4, 5, 6, 17, 18, 19, 20, 21, -1), Arrays.asList(Pattern.compile("18")));
    }

    private void verifyFilters(List<Integer> expectedLines, List filters) {
        Listener lineListener = (Listener) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Listener.class}, new LineListenerInvocationHandler());
        scan(lineListener, filters);
        assertEquals(expectedLines, filteredLines());
    }

    private List<Integer> filteredLines() {
        List<Integer> lines = new ArrayList<Integer>();
        for (Object[] arguments : argumentList) {
            int line = -1;
            if (arguments != null && arguments.length > 0) {
                line = (Integer) arguments[arguments.length - 1];
            }
            lines.add(line);
        }
        return lines;
    }

    private void scan(Listener listener, List filters) {
        Listener filterListener = new FilterListener(listener, filters);
        Parser parser = new Parser(filterListener);
        Lexer lexer = new I18nLexer(parser);
        lexer.scan(input, "test.feature", 0);
    }
}
