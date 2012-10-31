package gherkin.formatter.visitors;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.DataTableRow;
import gherkin.formatter.model.DocString;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Visitor;

import static gherkin.formatter.PrettyFormatter.escapeTripleQuotes;
import static gherkin.formatter.PrettyFormatter.indent;

public class PrettyVisitor implements Visitor {
    private static final CharSequence EOL = "\n";
    private final Appendable out;

    public PrettyVisitor(Appendable out) {
        this.out = out;
    }

    @Override
    public void visitFeature(Feature feature, Next next) throws Exception {
        out.append(feature.getKeyword()).append(": ").append(feature.getName()).append(EOL);
        next.next();
    }

    @Override
    public void visitBackground(Background background, Next next) throws Exception {
        out.append("  ").append(background.getKeyword()).append(": ").append(background.getName()).append(EOL);
        next.next();
    }

    @Override
    public void visitScenario(Scenario scenario, Next next) throws Exception {
        out.append("  ").append(scenario.getKeyword()).append(": ").append(scenario.getName()).append(EOL);
        next.next();
    }

    @Override
    public void visitScenarioOutline(ScenarioOutline scenarioOutline, Next next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visitStep(Step step, Next next) throws Exception {
        out.append("    ").append(step.getKeyword()).append(step.getName()).append(EOL);
        next.next();
    }

    @Override
    public void visitDataTableRow(DataTableRow row, Next next) throws Exception {
        out.append("      |");
        for (String cell : row.getCells()) {
            out.append(" ").append(cell).append(" |");
        }
        out.append(EOL);
        next.next();
    }

    @Override
    public void visitExamples(Examples examples) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visitDocString(DocString docString, Next next) throws Exception {
        out.append("      \"\"\"").append(docString.getContentType()).append(EOL);
        out.append(escapeTripleQuotes(indent(docString.getValue(), "      ")));
        out.append(EOL).append("      \"\"\"").append(docString.getContentType()).append(EOL);
    }

    public Next createNext() {
        return new Next(this);
    }
}
