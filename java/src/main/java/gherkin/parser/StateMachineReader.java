package gherkin.parser;

import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.En;
import gherkin.util.FixJava;

import java.util.ArrayList;
import java.util.List;

public class StateMachineReader implements Listener {
    private final String machinePath;
    private List<List<String>> transitionTable;

    public StateMachineReader(String name) {
        machinePath = "/gherkin/parser/" + name + ".txt";
    }

    public List<List<String>> transitionTable() {
        transitionTable = new ArrayList<List<String>>();
        Lexer lexer = new En(this);
        lexer.scan(FixJava.readResource(machinePath));
        return transitionTable;
    }

    @Override
    public void tag(String tag, Integer line) {
    }

    @Override
    public void comment(String comment, Integer line) {
    }

    @Override
    public void feature(String keyword, String name, String description, Integer line) {
    }

    @Override
    public void background(String keyword, String name, String description, Integer line) {
    }

    @Override
    public void scenario(String keyword, String name, String description, Integer line) {
    }

    @Override
    public void scenarioOutline(String keyword, String name, String description, Integer line) {
    }

    @Override
    public void examples(String keyword, String name, String description, Integer line) {
    }

    @Override
    public void step(String keyword, String name, Integer line) {
    }

    @Override
    public void docString(String contentType, String content, Integer line) {
    }

    @Override
    public void eof() {
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String featureURI, Integer line) {
    }

    public void row(List<String> cells, Integer line) {
        transitionTable.add(cells);
    }
}
