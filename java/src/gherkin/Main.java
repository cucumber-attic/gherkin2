package gherkin;

import gherkin.formatter.PrettyFormatter;
import gherkin.parser.Parser;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser p = new Parser(new PrettyFormatter(System.out, true));
        Lexer l = new I18nLexer(p);

        CharSequence input = FixJava.readReader(new FileReader(args[0]));
        l.scan(input);
    }

}
