package gherkin;

import gherkin.formatter.Formatter;
import gherkin.parser.Parser;

public class GherkinParser implements FeatureParser {
    private final Parser parser;

    public GherkinParser(Formatter formatter) {
        parser = new Parser(formatter);
    }

    public void parse(String src, String uri, int offset) {
        parser.parse(src, uri, offset);
    }
}
