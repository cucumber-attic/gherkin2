package gherkin.formatter;

import gherkin.formatter.model.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class PrettyFormatterTest {
    @Test
    public void testShouldPrintNiceColorsDirScenario() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, false);
        f.scenario(new Scenario(Collections.<Comment>emptyList(), Collections.<Tag>emptyList(), "Scenario", "a scenario", "", 1));
        f.step(new Step(new ArrayList<Comment>(), "Given ", "I have 6 cukes", 1));
        sleep(1000);
        f.match(new Match(Arrays.asList(new Argument(7, "6")), "somewhere.brainfuck", null));
        sleep(1000);
        f.result(new Result("failed", 55, "Something\nbad\nhappened"));
    }

    @Test
    public void testShouldPrintNiceColorsDirScenarioOutline() throws UnsupportedEncodingException, InterruptedException {
        PrettyFormatter f = new PrettyFormatter(System.out, false, true);

        List<Comment> comments = Collections.<Comment>emptyList();
        List<Tag> tags = Collections.<Tag>emptyList();

        f.uri("thefile.feature");
        f.feature(new Feature(comments, tags, "Feature", "Hello", "A description", 1));

        f.scenarioOutline(new ScenarioOutline(comments, tags, "Scenario Outline", "an outline", "", 2));
        f.step(new Step(comments, "Given ", "the <container> contains <ingredient>", 3));
        f.step(new Step(comments, "When ", "I add <liquid>", 4));
        f.step(new Step(comments, "And ", "serve it to my guests", 5));
        f.step(new Step(comments, "Then ", "they'll be eating \"<dish>\"", 6));

        List<Row> rows = asList(
                new Row(comments, asList("container", "ingredient", "liquid", "dish"), 8),
                new Row(comments, asList("bowk", "oats", "milk", "oatmeal"), 9),
                new Row(comments, asList("glass", "guinnes", "champagne", "black velvet"), 10)
        );
        f.examples(new Examples(comments, tags, "Examples", "", "", 7, rows));

        List<Argument> args = Collections.emptyList();
        String location = "somewhere.brainfuck";

        f.match(new Match(args, location, asList(0, 1)));
        sleep(2000);
        f.result(new Result("passed", 1, null));

        f.match(new Match(args, location, asList(2)));
        sleep(2000);
        f.result(new Result("failed", 1, "some\nlong\nass\nmessage"));

        f.match(new Match(args, location, Collections.<Integer>emptyList()));
        sleep(2000);
        f.result(new Result("skipped", 1, null));

        f.match(new Match(args, location, asList(3)));
        sleep(2000);
        f.result(new Result("skipped", 1, null));


        f.match(new Match(args, location, asList(0, 1)));
        sleep(2000);
        f.result(new Result("passed", 1, null));

        f.match(new Match(args, location, asList(2)));
        sleep(2000);
        f.result(new Result("skipped", 1, null));

        f.match(new Match(args, location, Collections.<Integer>emptyList()));
        sleep(2000);
        f.result(new Result("failed", 1, "failure\non step without\na column"));

        f.match(new Match(args, location, asList(3)));
        sleep(2000);
        f.result(new Result("undefined", 1, null));

        f.eof();
    }

    private void sleep(int millis) throws InterruptedException {
        if("true".equals(System.getProperty("cucumber.sleep"))) {
            Thread.sleep(millis);
        }
    }
}
