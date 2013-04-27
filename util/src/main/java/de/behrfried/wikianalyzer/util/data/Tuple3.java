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
 * Class for storing three values.
 * @author marcus
 *
 * @param <E> specifies the type of the first value
 * @param <G> specifies the type of the second value
 * @param <I> specifies the type of the third value
 */
public class Tuple3<E, G, I> {

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
	 * Creates an Tuple3 with the passed values.
	 * @param item1 the first value
	 * @param item2 the second value
	 * @param item3 the third value
	 */
	public Tuple3(E item1, G item2, I item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
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
	 * Calculates a hash code based on each item this Tuple3 contains.
	 * 
	 * @return a hash code based on each item this Tuple3 contains
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item1 == null) ? 0 : item1.hashCode());
		result = prime * result + ((item2 == null) ? 0 : item2.hashCode());
		result = prime * result + ((item3 == null) ? 0 : item3.hashCode());
		return result;
	}

	/**
	 * Compares this Tuple3 with the passed {@link Object}. Only an instance of
	 * Tuple3 can return true.
	 * 
	 * @param obj
	 * @return true if 'obj' is an instance of Tuple3 and all items 'equals' return true,
	 *         otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) obj;
		if (item1 == null) {
			if (other.item1 != null) {
				return false;
			}
		} else if (!item1.equals(other.item1)) {
			return false;
		}
		if (item2 == null) {
			if (other.item2 != null) {
				return false;
			}
		} else if (!item2.equals(other.item2)) {
			return false;
		}
		if (item3 == null) {
			if (other.item3 != null) {
				return false;
			}
		} else if (!item3.equals(other.item3)) {
			return false;
		}
		return true;
	}
	
	
}

