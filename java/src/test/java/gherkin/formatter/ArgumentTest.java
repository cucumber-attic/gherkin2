package gherkin.formatter;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ArgumentTest {
    @Test
    public void shouldFormatAscii() throws UnsupportedEncodingException {
        assertEquals("I have [[[[åtte] cukes i [[[[bøtta]", Argument.format("I have åtte cukes i bøtta", new ArgumentFormat("[[[[", "]"),
                new Argument(7, "åtte"),
                new Argument(21, "bøtta")
        ));
    }
}
