package gherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.NullFormatter;
import gherkin.formatter.PrettyFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;

import java.io.*;

public class Main {
    private FileFilter featureFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.isDirectory() || file.getName().endsWith(".feature");
        }
    };

    private Lexer lexer;
    private final Writer out;

    public Main(final Writer out, boolean prettyOrNull) {
        this.out = out;
        final Formatter formatter = prettyOrNull ? new PrettyFormatter(out, true) : new NullFormatter() {
            @Override
            public void step(String keyword, String name, int line) {
                try {
                    out.append('.').flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Parser parser = new Parser(formatter);
        lexer = new I18nLexer(parser);
    }

    private void scanAll(File file) throws IOException {
        walk(file);
        out.append('\n');
        out.close();
    }

    private void walk(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles(featureFilter)) {
                walk(child);
            }
        } else {
            parse(file);
        }
    }

    private void parse(File file) {
        try {
            String input = FixJava.readReader(new FileReader(file));
            lexer.scan(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main(new OutputStreamWriter(System.out, "UTF-8"), args.length > 1).scanAll(new File(args[0]));
    }

}
