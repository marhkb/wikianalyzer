package de.behrfried.wikianalyzer.util.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
	}
	
	@Test
	public void testCreateTuple3() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		
		final Tuple3<String, Integer, Character> result = Tuple.create(arg1, arg2, arg3);
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
	}
	
	@Test
	public void testCreateTuple4() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		
		final Tuple4<String, Integer, Character, Double> result = Tuple.create(arg1, arg2, arg3, arg4);
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
		assertEquals(arg4, result.getItem4(), 0.0);
	}
	
	@Test
	public void testCreateTuple5() {
		final String arg1 = "Hello World";
		final int arg2 = 15000;
		final char arg3 = 'o';
		final double arg4 = 1312.0023;
		final Object arg5 = new Object();
		
		final Tuple5<String, Integer, Character, Double, Object> result = Tuple.create(arg1, arg2, arg3, arg4, arg5);
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
		assertEquals(arg4, result.getItem4(), 0.0);
		assertEquals(arg5, result.getItem5());
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
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
		assertEquals(arg4, result.getItem4(), 0.0);
		assertEquals(arg5, result.getItem5());
		assertEquals(arg6, result.getItem6());
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
		
		final Tuple7<String, Integer, Character, Double, Object, List<String>, StringBuilder> result = Tuple.create(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
		assertEquals(arg4, result.getItem4(), 0.0);
		assertEquals(arg5, result.getItem5());
		assertEquals(arg6, result.getItem6());
		assertEquals(arg7, result.getItem7());
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
		
		final Tuple8<String, Integer, Character, Double, Object, List<String>, StringBuilder, Object> result = 
				Tuple.create(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
		
		assertEquals(arg1, result.getItem1());
		assertEquals(arg2, result.getItem2().intValue());
		assertEquals(arg3, result.getItem3().charValue());
		assertEquals(arg4, result.getItem4(), 0.0);
		assertEquals(arg5, result.getItem5());
		assertEquals(arg6, result.getItem6());
		assertEquals(arg7, result.getItem7());
		assertEquals(arg8, result.getItem8());
	}
}
