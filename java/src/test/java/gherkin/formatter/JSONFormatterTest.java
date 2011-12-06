package gherkin.formatter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import gherkin.formatter.Formatter;
import gherkin.formatter.JSONFormatter;

import java.io.PrintStream;

import org.junit.Test;

public class JSONFormatterTest {

    @Test
    public void shouldNotCloseProvidedStream() {
        PrintStream out = mock(PrintStream.class);
        Formatter formatter = new JSONFormatter(out);
        formatter.close();
        verify(out, never()).close();
    }
    
}
