package gherkin.formatter;

import gherkin.model.Step;

public interface SomeRuntime {
    void run(Step step, Reporter reporter, boolean outline);
}
