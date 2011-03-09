package gherkin.formatter;

import gherkin.model.*;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PrettyPrinter implements NewVisitor {
    private final PrintWriter out;

    public PrettyPrinter(PrintStream out) {
        this(new OutputStreamWriter(out, Charset.forName("UTF-8")));
    }

    public PrettyPrinter(Writer out) {
        this.out = new PrintWriter(out, true);
    }

    public void visitFeature(Feature feature, It it) {
        out.println(feature.getKeyword() + " " + feature.getName());
        it.next();
    }

    public void visitBackground(Background background, It it) {
        it.next();
    }

    public void visitScenario(Scenario scenario, It it) {
        it.next();
    }

    public void visitScenarioOutline(ScenarioOutline scenarioOutline, It it) {
        it.next();
    }

    public void visitStep(Step step, It it) {
        it.next();
    }

    public void visitExamples(Examples examples, It it) {
        it.next();
    }

    public void visitExampleScenario(ExampleScenario exampleScenario, It it) {
        it.next();
    }
}
