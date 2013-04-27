package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Various tests on {@link Tuple4}
 * @author marcus
 * 
 */
public class Tuple4Test {

	/**
	 * Tests whether a {@link Tuple4}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testConstruction() {
		final int arg1 = 12990;
		final String arg2 = "Hello World!";
		final Double arg3 = -11942.48266;
		final Object arg4 = new Object();

		final Tuple4<Integer, String, Double, Object> result = new Tuple4<Integer, String, Double, Object>(
				arg1, arg2, arg3, arg4);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
		assertEquals(arg3, result.getItem3());
		assertEquals(arg4, result.getItem4());
	}
}
