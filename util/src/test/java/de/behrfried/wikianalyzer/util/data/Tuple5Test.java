package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Various tests on {@link Tuple5}
 * @author marcus
 * 
 */
public class Tuple5Test {

	/**
	 * Tests whether a {@link Tuple5}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testConstruction() {
		final int arg1 = 12990;
		final String arg2 = "Hello World!";
		final Double arg3 = -11942.48266;
		final Object arg4 = new Object();
		final StringBuilder arg5 = null;

		final Tuple5<Integer, String, Double, Object, StringBuilder> result = 
				new Tuple5<Integer, String, Double, Object, StringBuilder>(arg1, arg2, arg3, arg4, arg5);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
		assertEquals(arg3, result.getItem3());
		assertEquals(arg4, result.getItem4());
		assertEquals(arg5, result.getItem5());
	}
}
