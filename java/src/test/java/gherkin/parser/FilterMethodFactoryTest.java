package gherkin.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class FilterMethodFactoryTest {

	@Test
	public void getFilterMethod_withString_returnsTagFilterMethod() throws Exception {
		IFilterMethod method = new FilterMethodFactory().getFilterMethod(String.class);
		assertEquals(TagFilterMethod.class, method.getClass());
	}

	@Test
	public void getFilterMethod_withLong_returnsLineFilterMethod() throws Exception {
		IFilterMethod method = new FilterMethodFactory().getFilterMethod(Long.class);
		assertEquals(LineFilterMethod.class, method.getClass());
	}

	@Test
	public void getFilterMethod_withOther_returnsNameFilterMethod() throws Exception {
		IFilterMethod method = new FilterMethodFactory().getFilterMethod(Object.class);
		assertEquals(NameFilterMethod.class, method.getClass());
	}
	
}
