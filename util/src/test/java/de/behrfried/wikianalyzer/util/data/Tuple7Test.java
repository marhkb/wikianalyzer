/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 
package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Various tests on {@link Tuple7}
 * @author marcus
 * 
 */
public class Tuple7Test {

	/**
	 * Tests whether a {@link Tuple7}'s constructor sets up the instance
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
		final boolean arg7 = false;

		final Tuple7<Integer, String, Double, Object, StringBuilder, Character, Boolean> result = 
				new Tuple7<Integer, String, Double, Object, StringBuilder, Character, Boolean>(arg1, arg2, arg3, arg4, arg5, arg6, arg7);

		assertEquals(arg1, result.getItem1().intValue());
		assertEquals(arg2, result.getItem2());
		assertEquals(arg3, result.getItem3().doubleValue(), 0.0);
		assertEquals(arg4, result.getItem4());
		assertEquals(arg5, result.getItem5());
		assertEquals(arg6, result.getItem6().charValue());
		assertEquals(arg7, result.getItem7().booleanValue());
	}
}
