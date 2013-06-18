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

package de.behrfried.wikianalyzer.wawebapp.client.data;

import de.behrfried.wikianalyzer.wawebapp.client.util.data.Tuple2;
import org.junit.Assert;
import org.junit.Test;


/**
 * Various tests on {@link Tuple2}
 * 
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

		final Tuple2<Integer, String> result = new Tuple2<Integer, String>(arg1, arg2);

		Assert.assertEquals(arg1, result.getItem1().intValue());
		Assert.assertEquals(arg2, result.getItem2());
	}
}
