package gherkin.listener;

import gherkin.listener.filter.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


public class FilterMethodFactoryTest {

    @Test
    public void getFilterMethod_withString_returnsTagFilterMethod() {
        FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList("@tag"));
        assertEquals(TagFilterMethod.class, method.getClass());
    }

    @Test
    public void getFilterMethod_withLong_returnsLineFilterMethod() {
        FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList(44));
        assertEquals(LineFilterMethod.class, method.getClass());
    }

    @Test
    public void getFilterMethod_withOther_returnsNameFilterMethod() {
        FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList(Pattern.compile("cukes")));
        assertEquals(NameFilterMethod.class, method.getClass());
    }

}
