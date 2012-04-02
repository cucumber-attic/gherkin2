package gherkin.formatter;

import java.util.List;

public class StepPrinter {
    public void writeStep(NiceAppendable out, Format textFormat, Format argFormat, String stepName, List<Argument> arguments) {
        int textStart = 0;
        for (Argument argument : arguments) {
            // can be null if the argument is missing.
            if (argument.getOffset() != null) {
                String text = stepName.substring(textStart, argument.getOffset());
                out.append(textFormat.text(text));
            }
            String val = argument.getVal();
            // val can be null if the argument isn't there, for example @And("(it )?has something")
            if (val != null) {
                out.append(argFormat.text(val));
                textStart = argument.getOffset() + argument.getVal().length();
            }
        }
        if (textStart != stepName.length()) {
            String text = stepName.substring(textStart, stepName.length());
            out.append(textFormat.text(text));
        }
    }
}
