package gherkin.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;


public class FilterMethodFactoryTest {

	@Test
	public void getFilterMethod_withString_returnsTagFilterMethod()  {
		FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList("@tag"));
		assertEquals(TagFilterMethod.class, method.getClass());
	}

	@Test
	public void getFilterMethod_withLong_returnsLineFilterMethod()  {
		FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList(44));
		assertEquals(LineFilterMethod.class, method.getClass());
	}

	@Test
	public void getFilterMethod_withOther_returnsNameFilterMethod()  {
		FilterMethod method = new FilterMethodFactory().getFilterMethod(Arrays.asList(Pattern.compile("cukes")));
		assertEquals(NameFilterMethod.class, method.getClass());
	}
	
}
