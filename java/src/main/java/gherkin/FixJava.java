package gherkin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FixJava {
    public interface Mapper {
        String map(String string);
    }

    public static String join(List<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s : strings) {
            if (i != 0) sb.append(separator);
            sb.append(s);
            i++;
        }
        return sb.toString();
    }

    public static List<String> map(List<String> strings, Mapper mapper) {
        List<String> result = new ArrayList<String>(strings.size());
        for (String s : strings) {
            result.add(mapper.map(s));
        }
        return result;
    }

    public static String readResource(String filePath) throws RuntimeException {
        Reader reader = new InputStreamReader(FixJava.class.getResourceAsStream(filePath));
        return readReader(reader);
    }

    public static String readReader(Reader reader) throws RuntimeException {
        final char[] buffer = new char[0x10000];
        StringBuilder sb = new StringBuilder();
        int read;
        do {
            try {
                read = reader.read(buffer, 0, buffer.length);
                if (read > 0) {
                    sb.append(buffer, 0, read);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (read >= 0);
        return sb.toString();
    }
}
