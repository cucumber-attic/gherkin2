package gherkin;

import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;
import gherkin.formatter.NullFormatter;
import gherkin.formatter.PrettyFormatter;
import gherkin.parser.FormatterListener;
import gherkin.parser.Parser;
import gherkin.parser.Row;
import gherkin.util.FixJava;

import java.io.*;
import java.util.List;

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
            public void step(List<String> comments, String keyword, String name, int line, List<Row> stepTable, String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {
                step();
            }

            public void step(List<String> comments, String keyword, String name, int line, String stepString,            String status, Throwable thrown, List<Argument> arguments, String stepdefLocation) {
                step();
            }
            private void step() {
                try {
                    out.append('.').flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        FormatterListener formatterListener = new FormatterListener(formatter);
        Parser parser = new Parser(formatterListener);
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
            lexer.scan(input, file.getPath(), 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main(new OutputStreamWriter(System.out, "UTF-8"), args.length > 1).scanAll(new File(args[0]));
    }

}
