package gherkin.formatter;

import java.util.List;

public class StepPrinter {
    public void writeStep(NiceAppendable out, Format textFormat, Format argFormat, String stepName, List<Argument> arguments) {
        int textStart = 0;
        for (Argument argument : arguments) {
            if (argument.getOffset() != 0) {
                String text = stepName.substring(textStart, argument.getOffset());
                out.append(textFormat.text(text));
            }
            String val = argument.getVal();
            out.append(argFormat.text(val));
            textStart = argument.getOffset() + argument.getVal().length();
        }
        if (textStart != stepName.length()) {
            String text = stepName.substring(textStart, stepName.length());
            out.append(textFormat.text(text));
        }
    }
}
