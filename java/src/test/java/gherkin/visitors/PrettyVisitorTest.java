package gherkin.visitors;

import gherkin.formatter.model.Feature;
import gherkin.formatter.visitors.PrettyVisitor;
import gherkin.parser.AstListener;
import gherkin.parser.Parser;
import org.junit.Test;

public class PrettyVisitorTest {
    @Test
    public void prints_pretty_features() throws Exception {
        AstListener l = new AstListener();
        l.feature("Feature", "Test", "Desc", 1);
        l.scenario("Scenario", "Test", "Desc", 2);
        l.step("Given ", "Test", 3);
        l.eof();

        Feature f = l.getFeature();
        PrettyVisitor visitor = new PrettyVisitor(System.out);
        f.accept(visitor, visitor.createNext());
    }

    @Test
    public void prints_pretty_features_from_parser() throws Exception {
        AstListener l = new AstListener();
        Parser parser = new Parser(l);
        String gherkin = "" +
                "Feature: Test\n" +
                "  Scenario: Test\n" +
                "    Given DataTable\n" +
                "      | a   | bb   |\n" +
                "      | ccc | dddd |\n" +
                "    When DocString\n" +
                "      \"\"\"\n" +
                "      Le Doc String\n" +
                "      \"\"\"\n" +
                "";
        parser.parse(gherkin, "some.feature", 0);

        Feature f = l.getFeature();
        PrettyVisitor visitor = new PrettyVisitor(System.out);
        f.accept(visitor, visitor.createNext());
    }
}
