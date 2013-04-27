package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Various tests on {@link Tuple6}
 * @author marcus
 * 
 */
public class Tuple6Test {

	/**
	 * Tests whether a {@link Tuple6}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testConstruction() {
		final int arg1 = 12990;
		final String arg2 = "Hello World!";
		final double arg3 = -11942.48266;
		final Object arg4 = new Object();
		final StringBuilder arg5 = null;
		final char arg6 = 'z';

		final Tuple6<Integer, String, Double, Object, StringBuilder, Character> result = 
				new Tuple6<Integer, String, Double, Object, StringBuilder, Character>(arg1, arg2, arg3, arg4, arg5, arg6);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
		assertEquals(arg3, result.getItem3().doubleValue(), 0.0);
		assertEquals(arg4, result.getItem4());
		assertEquals(arg5, result.getItem5());
		assertEquals(arg6, result.getItem6().charValue());
	}
}
