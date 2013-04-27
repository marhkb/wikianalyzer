package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Various tests on {@link Tuple2}
 * @author marcus
 * 
 */
public class Tuple2Test {

	/**
	 * Tests whether a {@link Tuple2}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testConstruction() {
		final int arg1 = 12990;
		final String arg2 = "Hello World!";

		final Tuple2<Integer, String> result = new Tuple2<Integer, String>(
				arg1, arg2);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
	}
}
