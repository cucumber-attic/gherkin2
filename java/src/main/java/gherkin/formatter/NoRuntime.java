package gherkin.formatter;

import gherkin.model.Step;

public class NoRuntime implements SomeRuntime {
    public void run(Step step, Reporter reporter, boolean outline) {
    }
}
