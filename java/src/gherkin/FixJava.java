package gherkin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class FixJava {
    public static String join(List<String> strings, String separator) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String s : strings) {
            if (i != 0) sb.append(separator);
            sb.append(s);
            i++;
        }
        return sb.toString();
    }

    public static String readResourceAsString(String filePath) throws IOException {
        Reader machine = new InputStreamReader(FixJava.class.getResourceAsStream(filePath));

        final char[] buffer = new char[0x10000];
        StringBuilder out = new StringBuilder();
        int read;
        do {
            read = machine.read(buffer, 0, buffer.length);
            if (read > 0) {
                out.append(buffer, 0, read);
            }
        } while (read >= 0);
        return out.toString();
    }
}
