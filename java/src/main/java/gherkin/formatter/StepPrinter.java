package gherkin.formatter;

import java.io.PrintWriter;
import java.util.List;

public class StepPrinter {
    public void writeStep(PrintWriter out, Format textFormat, Format argFormat, String stepName, List<Argument> arguments) {
        int textStart = 0;
        for (Argument argument : arguments) {
            if (argument.getOffset() != 0) {
                String text = stepName.substring(textStart, argument.getOffset());
                textFormat.writeText(out, text);
            }
            String val = argument.getVal();
            argFormat.writeText(out, val);
            textStart = argument.getOffset() + argument.getVal().length();
        }
        if (textStart != stepName.length()) {
            String text = stepName.substring(textStart, stepName.length());
            textFormat.writeText(out, text);
        }
    }
}
