package gherkin.formatter;

import java.util.List;

public class NullFormatter implements Formatter {

    public void feature(List<String> comments, List<String> tags, String keyword, String name, String description, String uri) {

    }

    public void background(List<String> comments, String keyword, String name, String description, int line) {

    }

    public void scenario(List<String> comments, List<String> tags, String keyword, String name, String description, int line) {

    }

    public void scenarioOutline(List<String> comments, List<String> tags, String keyword, String name, String description, int line) {

    }

    public void examples(List<String> comments, List<String> tags, String keyword, String name, String description, int line, List<List<String>> exampleRows) {

    }

    public void step(List<String> comments, String keyword, String name, int line, List<List<String>> stepTable, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {

    }

    public void step(List<String> comments, String keyword, String name, int line, String stepString, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {

    }

    public void eof() {

    }

    public void table(List<List<String>> rows) {

    }
}
