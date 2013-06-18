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

import de.behrfried.wikianalyzer.wawebapp.client.util.data.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author marcus
 * 
 */
public class TupleTest {

	@Test
	public void testCreateTuple2() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;

		final Tuple2<String, Integer> result = Tuple.create(arg1, arg2);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
	}

	@Test
	public void testCreateTuple3() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';

		final Tuple3<String, Integer, Character> result = Tuple.create(arg1, arg2, arg3);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
	}

	@Test
	public void testCreateTuple4() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;

		final Tuple4<String, Integer, Character, Double> result = Tuple.create(arg1, arg2, arg3, arg4);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
		Assert.assertEquals(arg4, result.getItem4(), 0.0);
	}

	@Test
	public void testCreateTuple5() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		final Object arg5 = new Object();

		final Tuple5<String, Integer, Character, Double, Object> result = Tuple.create(arg1, arg2, arg3, arg4, arg5);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
		Assert.assertEquals(arg4, result.getItem4(), 0.0);
		Assert.assertEquals(arg5, result.getItem5());
	}

	@Test
	public void testCreateTuple6() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		final Object arg5 = new Object();

		final List<String> arg6 = new ArrayList<String>();
		arg6.add("Hello");
		arg6.add("World");

		final Tuple6<String, Integer, Character, Double, Object, List<String>> result = Tuple.create(arg1, arg2, arg3, arg4, arg5, arg6);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
		Assert.assertEquals(arg4, result.getItem4(), 0.0);
		Assert.assertEquals(arg5, result.getItem5());
		Assert.assertEquals(arg6, result.getItem6());
	}

	@Test
	public void testCreateTuple7() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		final Object arg5 = new Object();

		final List<String> arg6 = new ArrayList<String>();
		arg6.add("Hello");
		arg6.add("World");

		final StringBuilder arg7 = null;

		final Tuple7<String, Integer, Character, Double, Object, List<String>, StringBuilder> result = Tuple.create(arg1, arg2, arg3, arg4, arg5,
		        arg6, arg7);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
		Assert.assertEquals(arg4, result.getItem4(), 0.0);
		Assert.assertEquals(arg5, result.getItem5());
		Assert.assertEquals(arg6, result.getItem6());
		Assert.assertEquals(arg7, result.getItem7());
	}

	@Test
	public void testCreateTuple8() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		final Object arg5 = new Object();

		final List<String> arg6 = new ArrayList<String>();
		arg6.add("Hello");
		arg6.add("World");

		final StringBuilder arg7 = null;
		final Object arg8 = arg1;

		final Tuple8<String, Integer, Character, Double, Object, List<String>, StringBuilder, Object> result = Tuple.create(arg1, arg2, arg3, arg4,
		        arg5, arg6, arg7, arg8);

		Assert.assertEquals(arg1, result.getItem1());
		Assert.assertEquals(arg2, result.getItem2().intValue());
		Assert.assertEquals(arg3, result.getItem3().charValue());
		Assert.assertEquals(arg4, result.getItem4(), 0.0);
		Assert.assertEquals(arg5, result.getItem5());
		Assert.assertEquals(arg6, result.getItem6());
		Assert.assertEquals(arg7, result.getItem7());
		Assert.assertEquals(arg8, result.getItem8());
	}
}
