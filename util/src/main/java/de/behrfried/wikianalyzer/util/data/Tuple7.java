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

/**
 * 
 * Class for storing seven values.
 * @author marcus
 *
 * @param <E> specifies the type of the first value
 * @param <G> specifies the type of the second value
 * @param <I> specifies the type of the third value
 * @param <K> specifies the type of the fourth value
 * @param <M> specifies the type of the fifth value
 * @param <O> specifies the type of the sixth value
 * @param <Q> specifies the type of the seventh value
 */
public class Tuple7<E, G, I, K, M, O, Q> {

	/**
	 * the first value
	 */
	private final E item1;
	
	/**
	 * the second value
	 */
	private final G item2;
	
	/**
	 * the third value
	 */
	private final I item3;
	
	/**
	 * the fourth value
	 */
	private final K item4;
	
	/**
	 * the fifth value
	 */
	private final M item5;
	
	/**
	 * the sixth value
	 */
	private final O item6;
	
	/**
	 * the seventh value
	 */
	private final Q item7;

	/**
	 * Creates an Tuple7 with the passed values.
	 * @param item1 the first value
	 * @param item2 the second value
	 * @param item3 the third value
	 * @param item4 the fourth value
	 * @param item5 the fifth value
	 * @param item6 the sixth value
	 * @param item7 the seventh value
	 */
	public Tuple7(E item1, G item2, I item3, K item4, M item5, O item6, Q item7) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
		this.item7 = item7;
	}

	/**
	 * Returns the first value.
	 * @return the first value
	 */
	public E getItem1() {
		return item1;
	}

	/**
	 * Returns the second value.
	 * @return the second value
	 */
	public G getItem2() {
		return item2;
	}

	/**
	 * Returns the third value.
	 * @return the third value
	 */
	public I getItem3() {
		return item3;
	}

	/**
	 * Returns the fourth value.
	 * @return the fourth value
	 */
	public K getItem4() {
		return item4;
	}

	/**
	 * Returns the fifth value.
	 * @return the fifth value
	 */
	public M getItem5() {
		return item5;
	}

	/**
	 * Returns the sixth value.
	 * @return the sixth value
	 */
	public O getItem6() {
		return item6;
	}
	
	/**
	 * Returns the seventh value.
	 * @return the seventh value
	 */
	public Q getItem7() {
		return item7;
	}

	
}

