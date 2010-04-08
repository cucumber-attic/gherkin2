package gherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.NullFormatter;
import gherkin.formatter.PrettyFormatter;
import gherkin.parser.ParseError;
import gherkin.parser.Parser;

import java.io.*;

public class Main {
    private FileFilter featureFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.isDirectory() || file.getName().endsWith(".feature");
        }
    };

    private Lexer lexer;

    public Main(OutputStream out, boolean prettyOrNull) throws UnsupportedEncodingException {
        Formatter formatter = prettyOrNull ? new PrettyFormatter(out, true) : new NullFormatter();
        Parser parser = new Parser(formatter);
        lexer = new I18nLexer(parser);
    }

    private void walk(File file) throws IOException {
        if(file.isDirectory()) {
            for(File child: file.listFiles(featureFilter)) {
                walk(child);
            }
        } else {
            parse(file);
        }
    }

    private void parse(File file) throws IOException {
        CharSequence input = FixJava.readReader(new FileReader(file));
        try {
            lexer.scan(input);
        } catch(ParseError e) {
            System.err.println(e.getMessage());
            System.err.println(input);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main(System.out, args.length > 1).walk(new File(args[0]));
    }
}
