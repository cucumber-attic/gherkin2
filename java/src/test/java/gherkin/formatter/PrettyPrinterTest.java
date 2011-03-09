package gherkin.formatter;

import gherkin.FeatureParser;
import gherkin.GherkinParser;
import gherkin.model.*;
import gherkin.util.FixJava;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class PrettyPrinterTest {
    private List<Comment> comments = emptyList();
    private List<Tag> tags = emptyList();

    @Test
    public void testShouldPrintNiceColorsDirScenario() throws UnsupportedEncodingException, InterruptedException {
        PrettyPrinterOld f = new PrettyPrinterOld(System.out, false);
        f.visitStep(new Step(comments, "Given ", "I have 6 cukes", 1));
        f.visitScenario(new Scenario(comments, tags, "Scenario", "a scenario", "", 1));
        sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck", null));
        sleep(1000);
        f.result(new Result("failed", 55, "Something\nbad\nhappened"));
    }

    @Test
    public void testShouldPrintNiceColorsDirScenarioOutline() throws UnsupportedEncodingException, InterruptedException {
        PrettyPrinterOld f = new PrettyPrinterOld(System.out, false);

        f.uri("thefile.feature");
        f.visitFeature(new Feature(comments, tags, "Feature", "Hello", "A description", 1));

        f.visitScenarioOutline(new ScenarioOutline(comments, tags, "Scenario Outline", "an outline", "", 2));
        f.visitStep(new Step(comments, "Given ", "the <container> contains <ingredient>", 3));
        f.visitStep(new Step(comments, "When ", "I add <liquid>", 4));
        f.visitStep(new Step(comments, "And ", "serve it to my guests", 5));
        f.visitStep(new Step(comments, "Then ", "they'll be eating \"<dish>\"", 6));

        List<Row> rows = asList(
                new Row(comments, asList("container", "ingredient", "liquid", "dish"), 8),
                new Row(comments, asList("bowk", "oats", "milk", "oatmeal"), 9),
                new Row(comments, asList("glass", "guinnes", "champagne", "black velvet"), 10)
        );
        f.visitExamples(new Examples(comments, tags, "Examples", "", "", 7, rows));

        List<Argument> args = emptyList();
        String location = "somewhere.brainfuck";

        f.match(new Match(args, location, asList(0, 1)));
        sleep(2000);
        f.result(new Result("passed", 1));

        f.match(new Match(args, location, asList(2)));
        sleep(2000);
        f.result(new Result("failed", 1, "some\nlong\nass\nmessage"));

        f.match(new Match(args, location, Collections.<Integer>emptyList()));
        sleep(2000);
        f.result(new Result("skipped", 1));

        f.match(new Match(args, location, asList(3)));
        sleep(2000);
        f.result(new Result("skipped", 1));


        f.match(new Match(args, location, asList(0, 1)));
        sleep(2000);
        f.result(new Result("passed", 1));

        f.match(new Match(args, location, asList(2)));
        sleep(2000);
        f.result(new Result("skipped", 1));

        f.match(new Match(args, location, Collections.<Integer>emptyList()));
        sleep(2000);
        f.result(new Result("failed", 1, "failure\non step without\na column"));

        f.match(new Match(args, location, asList(3)));
        sleep(2000);
        f.result(new Result("undefined", 1));

        f.eof();
    }

    @Test
    public void should_pretty_simple() {
        FeatureParser p = new GherkinParser();
        String gherkin = FixJava.readResource("/gherkin/simple.feature");
        Feature f = p.parse(gherkin, "gherkin/simple.feature", 0);
        NewVisitor pretty = new PrettyPrinter(System.out);
        f.accept(pretty);
    }


    private void sleep(int millis) throws InterruptedException {
        if("true".equals(System.getProperty("cucumber.sleep"))) {
            Thread.sleep(millis);
        }
    }
}
