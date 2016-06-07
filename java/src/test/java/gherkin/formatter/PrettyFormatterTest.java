package gherkin.formatter;

import gherkin.formatter.ansi.AnsiEscapes;
import gherkin.formatter.model.*;
import org.junit.Test;
import static gherkin.formatter.LocationMatcher.hasLocation;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import gherkin.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PrettyFormatterTest {
    private static final List<Comment> NO_COMMENTS = emptyList();
    private static final List<Tag> NO_TAGS = Collections.emptyList();

    @Test
    public void prints_nice_colors() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, NO_TAGS, "Scenario", "a scenario", "", 1, "a-scenario"));
        f.step(new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1, null, null));
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }

    @Test
    public void prints_table() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(NO_COMMENTS, Collections.<Tag>emptyList(), "Scenario", "a scenario", "", 1, "a-scenario"));
        ArrayList<DataTableRow> rows = new ArrayList<DataTableRow>() {{
            add(new DataTableRow(NO_COMMENTS, asList("un", "deux"), 1, Row.DiffType.NONE));
            add(new DataTableRow(NO_COMMENTS, asList("one", "two"), 1, Row.DiffType.DELETE));
            add(new DataTableRow(NO_COMMENTS, asList("en", "to"), 1, Row.DiffType.INSERT));
        }};
        Step step = new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1, rows, null);
        f.step(step);
        Thread.sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck"));
        Thread.sleep(1000);
        f.result(new Result("failed", 55L, "Something\nbad\nhappened"));
    }
    
    @Test
    public void shouldNotCloseProvidedStreamInDone() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new PrettyFormatter(out, true, true);
        formatter.done();
        verify(out, never()).close();
    }

    @Test
    public void shouldFlushAndCloseProvidedStreamInClose() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new PrettyFormatter(out, true, true);
        formatter.close();
        verify(out).flush();
        verify(out).close();
    }
    
    @Test
    public void shouldFormatAsDesigned() throws IOException {

        StringBuilder featureBuilder = new StringBuilder();
        featureBuilder.append("Feature: PrettyFormatter\n");
        featureBuilder.append("Scenario: Formmat beautifully\n");
        featureBuilder.append("When I have this table:\n");
        featureBuilder.append("\t|name|value|\n");
        featureBuilder.append("\t|a|b|\n");
        featureBuilder.append("Then should formatt beautifully.\n");
        String feature = featureBuilder.toString();

        List<String> lines = doFormatter(feature);

        assertEquals("Formatter produces unexpected quantity of lines. ", 7, lines.size());
        
        assertEquals("Feature: PrettyFormatter", lines.get(0));
        assertEquals("", lines.get(1));
        assertEquals("  Scenario: Formmat beautifully", lines.get(2));
        assertEquals("    When I have this table:", lines.get(3));
        assertEquals("      | name | value |", lines.get(4));
        assertEquals("      | a    | b     |", lines.get(5));
        assertEquals("    Then should formatt beautifully.", lines.get(6));

    }

    /**
     * CJK and other character sets often contain 'fullwidth' characters.
     * <p>
     * Fullwidth characters are only counted as a single character but occupy twice
     * the space of a typical ASCII character when using a fixed-width font.
     * <p>
     * For example, in Japanese this text is 2 characters, padded with 5 spaces to match the longer column below:
     * <ul>
     * <li>{@code 新機     |}
     * <li>{@code 123456 |}
     * </ul>
     *
     * <p>
     * The net result is that '|' and '#' characters will be misaligned when
     * printing {@code --i18n ja} or a feature containing a mix of normal and fullwidth characters.
     *
     * The only way to resolve this is to keep a running tally of half- and full-width characters,
     * then padding the output with a mix of trailing half- (u+0020) and full-width (u+3000) spaces.
     * <p>
     * Repeating the above example, we now have:
     * <ul>
     * <li>{@code 新機      |} (2 full-width chars + 6 half-width spaces)
     * <li>{@code 123456　　|} (6 half-width chars + 2 full-width spaces)
     * </ul>
     *
     * @see "http://en.wikipedia.org/wiki/Halfwidth_and_fullwidth_forms"
     *
     * @throws IOException
     */
    @Test
    public void shouldFormatAsDesignedWithFullWidthCharacters() throws IOException {

        StringBuilder featureBuilder = new StringBuilder();
        featureBuilder.append("# language: ja\n");
        featureBuilder.append("機能: PrettyFormatter with Japanese\n");
        featureBuilder.append("シナリオ: Formmat beautifully\n");
        featureBuilder.append("もしI have this table:\n");
        featureBuilder.append("\t|名前|?の値1|\n");
        featureBuilder.append("\t|ab12ａｂ１２|ﾊﾝｶｸ|\n");
        featureBuilder.append("ならばshould formatt beautifully.\n");
        String feature = featureBuilder.toString();

        List<String> lines = doFormatter(feature);

        assertEquals("Formatter produces unexpected quantity of lines. ", 8, lines.size());

        assertEquals("# language: ja", lines.get(0));
        assertEquals("機能: PrettyFormatter with Japanese", lines.get(1));
        assertEquals("", lines.get(2));
        assertEquals("  シナリオ: Formmat beautifully", lines.get(3));
        assertEquals("    もしI have this table:", lines.get(4));
        assertEquals("      | 名前　　     | ?の値1   |", lines.get(5));
        assertEquals("      | ab12ａｂ１２ | ﾊﾝｶｸ　　 |", lines.get(6));
        assertEquals("    ならばshould formatt beautifully.", lines.get(7));

    }

    @Test
    public void shouldAppendOnlyCompleteLinesAndFlushBetween() throws IOException {

        StringBuilder featureBuilder = new StringBuilder();
        featureBuilder.append("Feature: PrettyFormatter\n");
        featureBuilder.append("Scenario: Formmat beautifully\n");
        featureBuilder.append("When I have this table:\n");
        featureBuilder.append("\t|name|value|\n");
        featureBuilder.append("\t|a|b|\n");
        featureBuilder.append("Then should formatt beautifully.\n");
        String feature = featureBuilder.toString();

        Formatter formatter = new PrettyFormatter(new CheckingAppendable(), false, false);
        new Parser(formatter).parse(feature, "", 0);
        formatter.close();
    }

    class CheckingAppendable implements Appendable, Flushable {
        boolean flushed = true;

        void checkAppend(CharSequence csq) {
            if (!flushed) {
                fail("No flush before append: " + csq);
            }
            assertThat("Must only append complete lines", csq.toString(), endsWith("\n"));
            flushed = false;
        }

        @Override
        public Appendable append(CharSequence csq) throws IOException {
            checkAppend(csq);
            return this;
        }

        @Override
        public Appendable append(CharSequence csq, int start, int end) throws IOException {
            checkAppend(csq.subSequence(start, end));
            return this;
        }

        @Override
        public Appendable append(char c) throws IOException {
            checkAppend(String.valueOf(c));
            return this;
        }

        @Override
        public void flush() throws IOException {
            flushed = true;
        }
    }

    @Test
    public void whenIMissSomeCellsInARowShouldFill() throws IOException {

        StringBuilder featureBuilder = new StringBuilder();
        featureBuilder.append("Feature: PrettyFormatter\n");
        featureBuilder.append("Scenario: Formmat beautifully\n");
        featureBuilder.append("When I have this table:\n");
        featureBuilder.append("\t|name|value|\n");
        featureBuilder.append("\t|a|\n"); // <--- here is different
        featureBuilder.append("Then should formatt beautifully.\n");
        String feature = featureBuilder.toString();

        List<String> lines = doFormatter(feature);

        assertEquals("Formatter produces unexpected quantity of lines. ", 7, lines.size());

        assertEquals("      | a    |       |", lines.get(5));

    }

    @Test
    public void whenIAddSomeExtraCellsInARowShouldFillOthersRows() throws IOException {

        StringBuilder featureBuilder = new StringBuilder();
        featureBuilder.append("Feature: PrettyFormatter\n");
        featureBuilder.append("Scenario: Formmat beautifully\n");
        featureBuilder.append("When I have this table:\n");
        featureBuilder.append("\t|name|value|\n");
        featureBuilder.append("\t|a|b|c|\n"); // <--- here is different
        featureBuilder.append("Then should formatt beautifully.\n");

        String feature = featureBuilder.toString();

        List<String> lines = doFormatter(feature);

        assertEquals("Formatter produces unexpected quantity of lines. ", 7, lines.size());

        assertEquals("      | name | value |   |", lines.get(4));
        assertEquals("      | a    | b     | c |", lines.get(5));

    }

    @Test
    public void shouldHandleAllStepCallsBeforeAnyMatchResultCalls() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrettyFormatter formatter = createMonochromePrettyFormatter(baos);
        formatter.uri("test/resources/cucumber/examples/java/helloworld/helloworld.feature");
        callFeature(formatter, "Hello World", 1);
        callStartOfScenarioLifecycle(formatter, "Say hello", 3);
        callScenario(formatter, "Say hello", 3);
        callStep(formatter, "Given ", "I have a hello app with \"Howdy\"", 4);
        callStep(formatter, "When ", "I ask it to say hi", 5);
        callStep(formatter, "Then ", "it should answer with \"Howdy World\"", 6);
        callMatch(formatter, "HelloStepdefs.I_have_a_hello_app_with(String)", "Howdy", 25);
        callResult(formatter, "passed");
        callMatch(formatter, "HelloStepdefs.I_ask_it_to_say_hi()");
        callResult(formatter, "passed");
        callMatch(formatter, "HelloStepdefs.it_should_answer_with(String)", "Howdy World", 23);
        callResult(formatter, "passed");
        callEndOfScenarioLifecycle(formatter, "Say hello", 3);
        formatter.eof();
        formatter.done();
        formatter.close();

        assertEquals( // the hash signs are aligned, its just the \" that give an illusion non-alignment
                "Feature: Hello World\n" +
                "\n" +
                "  Scenario: Say hello                        # test/resources/cucumber/examples/java/helloworld/helloworld.feature:3\n" +
                "    Given I have a hello app with \"Howdy\"    # HelloStepdefs.I_have_a_hello_app_with(String)\n" +
                "    When I ask it to say hi                  # HelloStepdefs.I_ask_it_to_say_hi()\n" +
                "    Then it should answer with \"Howdy World\" # HelloStepdefs.it_should_answer_with(String)\n",
                baos.toString());
    }

    @Test
    public void shouldHandleInterleavedStepMatchResultCallsForScenario() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrettyFormatter formatter = createMonochromePrettyFormatter(baos);
        formatter.uri("test/resources/cucumber/examples/java/helloworld/helloworld.feature");
        callFeature(formatter, "Hello World", 1);
        callStartOfScenarioLifecycle(formatter, "Say hello", 3);
        callScenario(formatter, "Say hello", 3);
        callStep(formatter, "Given ", "I have a hello app with \"Howdy\"", 4);
        callMatch(formatter, "HelloStepdefs.I_have_a_hello_app_with(String)", "Howdy", 25);
        callResult(formatter, "passed");
        callStep(formatter, "When ", "I ask it to say hi", 5);
        callMatch(formatter, "HelloStepdefs.I_ask_it_to_say_hi()");
        callResult(formatter, "passed");
        callStep(formatter, "Then ", "it should answer with \"Howdy World\"", 6);
        callMatch(formatter, "HelloStepdefs.it_should_answer_with(String)", "Howdy World", 23);
        callResult(formatter, "passed");
        callEndOfScenarioLifecycle(formatter, "Say hello", 3);
        formatter.eof();
        formatter.done();
        formatter.close();

        assertEquals( // the hash signs are aligned, its just the \" that give an illusion non-alignment
                "Feature: Hello World\n" +
                "\n" +
                "  Scenario: Say hello                        # test/resources/cucumber/examples/java/helloworld/helloworld.feature:3\n" +
                "    Given I have a hello app with \"Howdy\"    # HelloStepdefs.I_have_a_hello_app_with(String)\n" +
                "    When I ask it to say hi                  # HelloStepdefs.I_ask_it_to_say_hi()\n" +
                "    Then it should answer with \"Howdy World\" # HelloStepdefs.it_should_answer_with(String)\n",
                baos.toString());
    }

    @Test
    public void shouldTreatStepWithMatchCallsWithoutResultAsSkipped() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrettyFormatter formatter = createColorPrettyFormatter(baos);
        formatter.uri("path/name.feature");
        callFeature(formatter, "A Feature", 1);
        callStartOfScenarioLifecycle(formatter, "A Scenario", 3);
        callScenario(formatter, "A Scenario", 3);
        callStep(formatter, "* ", "First step", 4);
        callMatch(formatter, "Stepdefs.First_step()");
        callStep(formatter, "* ", "Second step", 5);
        callMatch(formatter, "Stepdefs.Second_step()");
        callEndOfScenarioLifecycle(formatter, "A Scenario", 3);
        formatter.eof();
        formatter.done();
        formatter.close();

        List<String> lines = extractLines(baos);
        assertThat(lines.get(3), containsString("First step"));
        assertLineHasStatus(lines.get(3), "skipped");
        assertThat(lines.get(3), hasLocation());
        assertThat(lines.get(4), containsString("Second step"));
        assertLineHasStatus(lines.get(3), "skipped");
        assertThat(lines.get(4), hasLocation());
    }

    @Test
    public void shouldHandleResultCallsWithoutMatchForScenario() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrettyFormatter formatter = createColorPrettyFormatter(baos);
        formatter.uri("path/name.feature");
        callFeature(formatter, "A Feature", 1);
        callStartOfScenarioLifecycle(formatter, "A Scenario", 3);
        callScenario(formatter, "A Scenario", 3);
        callStep(formatter, "* ", "First step", 4);
        callResult(formatter, "undefined");
        callStep(formatter, "* ", "Second step", 5);
        callResult(formatter, "skipped");
        callEndOfScenarioLifecycle(formatter, "A Scenario", 3);
        formatter.eof();
        formatter.done();
        formatter.close();

        List<String> lines = extractLines(baos);
        assertThat(lines.get(3), containsString("First step"));
        assertLineHasStatus(lines.get(3), "undefined");
        assertThat(lines.get(3), not(hasLocation()));
        assertThat(lines.get(4), containsString("Second step"));
        assertLineHasStatus(lines.get(4), "skipped");
        assertThat(lines.get(4), not(hasLocation()));
    }

    @Test
    public void shouldPrintResultErrorMessagesRegerdlessOfMatchCalls() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrettyFormatter formatter = createMonochromePrettyFormatter(baos);
        formatter.uri("path/name.feature");
        callFeature(formatter, "A Feature", 1);
        callScenario(formatter, "A Scenario", 3);
        callStep(formatter, "* ", "First step", 4);
        callMatch(formatter, "Stepdefs.First_step()");
        callResult(formatter, "failed", "Error message 1");
        callStep(formatter, "* ", "Second step", 5);
        callResult(formatter, "skipped", "Error message 2");
        formatter.eof();
        formatter.done();
        formatter.close();

        assertEquals(
                "Feature: A Feature\n" +
                "\n" +
                "  Scenario: A Scenario # path/name.feature:3\n" +
                "    * First step       # Stepdefs.First_step()\n" +
                "      Error message 1\n" +
                "    * Second step\n" +
                "      Error message 2\n",
                baos.toString());
    }

    /**
     * Execute a formatting feature for many different scenarios.
     * 
     * @param feature
     * @return
     * @throws IOException
     */
    private List<String> doFormatter(String feature) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        Formatter formatter = new PrettyFormatter(out, true, false);
        new Parser(formatter).parse(feature, "", 0);
        formatter.close();

        List<String> lines = extractLines(baos);

        return lines;

    }

    private List<String> extractLines(ByteArrayOutputStream baos) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));

        String line;
        List<String> lines = new ArrayList<String>();
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            System.out.println(lineNumber+":"+line);
            lineNumber++;
            lines.add(line);
        }
        return lines;
    }

    private PrettyFormatter createMonochromePrettyFormatter(ByteArrayOutputStream baos) {
        PrintStream out = new PrintStream(baos);
        return new PrettyFormatter(out, true, true);
    }

    private PrettyFormatter createColorPrettyFormatter(ByteArrayOutputStream baos) {
        PrintStream out = new PrintStream(baos);
        return new PrettyFormatter(out, false, true);
    }

    private void callFeature(PrettyFormatter formatter, String name, int line) {
        formatter.feature(new Feature(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Feature", name, "", line, ""));
    }

    private void callStartOfScenarioLifecycle(PrettyFormatter formatter, String name, int line) {
        formatter.startOfScenarioLifeCycle(new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Scenario", name, "", line, ""));
    }

    private void callScenario(PrettyFormatter formatter, String name, int line) {
        formatter.scenario(new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Scenario", name, "", line, ""));
    }

    private void callStep(PrettyFormatter formatter, String keyword, String name, int line) {
        formatter.step(new Step(new ArrayList<Comment>(), keyword, name, line, null, null));
    }

    private void callMatch(PrettyFormatter formatter, String location) {
        formatter.match(new Match(Collections.<Argument>emptyList(), location));
    }

    private void callMatch(PrettyFormatter formatter, String location, String argumentValue, int offset) {
        Argument arg = new Argument(offset, argumentValue);
        List<Argument> argList = new ArrayList<Argument>();
        argList.add(arg);
        formatter.match(new Match(argList, location));
    }

    private void callResult(PrettyFormatter formatter, String status) {
        formatter.result(new Result(status, 0l, null));
    }

    private void callResult(PrettyFormatter formatter, String status, String errorMessage) {
        formatter.result(new Result(status, 0l, errorMessage));
    }

    private void callEndOfScenarioLifecycle(PrettyFormatter formatter, String name, int line) {
        formatter.startOfScenarioLifeCycle(new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Scenario", name, "", line, ""));
    }

    private void assertLineHasStatus(String line, String status) {
        if ("undefined".equalsIgnoreCase(status)) {
            assertThat(line, containsString(AnsiEscapes.YELLOW.toString()));

        } else if ("skipped".equalsIgnoreCase(status)) {
            assertThat(line, containsString(AnsiEscapes.CYAN.toString()));

        } else {
            fail("status not implemented");
        }
    }
}
