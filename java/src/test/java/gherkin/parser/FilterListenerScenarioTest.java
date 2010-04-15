package gherkin.parser;

import gherkin.I18nLexer;
import gherkin.Lexer;
import gherkin.Listener;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterListenerScenarioTest {
    private String input;
    private List<Object[]> argumentList = new ArrayList<Object[]>();

    private class LineListenerInvocationHandler implements InvocationHandler {
            public Object invoke(Object target, Method method, Object[] arguments) throws Throwable {
                argumentList.add(arguments);
                return null;
            }
    }

    @Before
    public void setInput() {
        input = "Feature: 1\n" +
                "  Scenario: 2\n" +
                "    Given 3\n" +
                "\n" +
                "  Scenario: 5\n" +
                "    Given 6";
    }

    @Test
    public void shouldReplayIdenticallyWhenLineFilterIsFeatureLine() throws Exception {
        verifyFilters(Arrays.asList(1,2,3,5,6, -1), Arrays.asList(1));
    }

    private void verifyFilters(List<Integer> expectedLines, List filters) throws Exception {
        Listener lineListener = (Listener) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Listener.class}, new LineListenerInvocationHandler());
        scan(lineListener, filters);
        assertEquals(expectedLines, filteredLines());
    }

    private List<Integer> filteredLines() {
        List<Integer> lines = new ArrayList<Integer>();
        for(Object[] arguments : argumentList) {
            int line = -1;
            if(arguments != null && arguments.length > 0) {
                line = (Integer) arguments[arguments.length - 1];
            }
            lines.add(line);
        }
        return lines;
    }

    private void scan(Listener listener, List filters) throws Exception {
        Listener filterListener = new FilterListener(listener, filters);
        Parser parser = new Parser(filterListener);
        Lexer lexer = new I18nLexer(parser);
        lexer.scan(input);
    }
}
