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
 * Class for storing two values.
 * @author marcus
 *
 * @param <E> specifies the type of the first value
 * @param <G> specifies the type of the second value
 */
public class Tuple2<E, G> {

	/**
	 * the first value
	 */
	private final E item1;
	
	/**
	 * the second value
	 */
	private final G item2;
	
	/**
	 * Creates an Tuple2 with the passed values.
	 * @param item1 the first value
	 * @param item2 the second value
	 */
	public Tuple2(E item1, G item2) {
		this.item1 = item1;
		this.item2 = item2;
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
}
