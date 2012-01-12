package gherkin.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FixJava {

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

    public static <T, R> List<R> map(List<T> objects, Mapper<T, R> mapper) {
        List<R> result = new ArrayList<R>(objects.size());
        for (T o : objects) {
            result.add(mapper.map(o));
        }
        return result;
    }

    public static String readResource(String resourcePath) throws RuntimeException {
        try {
            Reader reader = new InputStreamReader(FixJava.class.getResourceAsStream(resourcePath), "UTF-8");
            return readReader(reader);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
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
