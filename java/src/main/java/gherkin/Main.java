package gherkin;

import gherkin.formatter.Formatter;
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

    private Parser parser;
    private final Writer out;

    public Main(final Writer out) {
        this.out = out;
        final Formatter formatter = new PrettyFormatter(out, false, true);
        parser = new Parser(formatter);
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
            parser.parse(input, file.getPath(), 0);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main(new OutputStreamWriter(System.out, "UTF-8")).scanAll(new File(args[0]));
    }

}
