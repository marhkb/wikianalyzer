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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Various tests on {@link DataContainer}.
 * @author marcus
 *
 */
public class DataContainerTest {

	/**
	 * Tests whether a {@link DataContainer}'s constructor sets up the instance
	 * correctly.
	 */
	@Test
	public void testDataContainer() {
		final DataContainer<Boolean> result = new DataContainer<Boolean>(true);
		assertTrue(result.getValue());
	}

	/**
	 * Tests whether 'getValue' returns the correct value.
	 */
	@Test
	public void testGetValue() {
		final List<String> arg = new ArrayList<String>();
		arg.add("Hello");
		arg.add("World");
		
		final DataContainer<List<String>> result = new DataContainer<List<String>>(arg);
		
		result.getValue().add("!");
		
		assertEquals(arg, result.getValue());
	}

	/**
	 * Tests whether 'setValue' sets the correct value.
	 */
	@Test
	public void testSetValue() {
		final DataContainer<Integer> result = new DataContainer<Integer>(0);
		
		final int arg = -4532785;
		
		result.setValue(arg);
		
		assertEquals(arg, result.getValue().intValue());
	}

}
