package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Various tests on {@link Tuple3}
 * @author marcus
 * 
 */
public class Tuple3Test {

	/**
	 * Tests whether a {@link Tuple3}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testConstruction() {
		final int arg1 = 12990;
		final String arg2 = "Hello World!";
		final Double arg3 = -11942.48266;

		final Tuple3<Integer, String, Double> result = new Tuple3<Integer, String, Double>(
				arg1, arg2, arg3);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
		assertEquals(arg3, result.getItem3());
	}
}
