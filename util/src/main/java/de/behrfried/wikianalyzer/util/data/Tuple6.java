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
 * Class for storing six values.
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the type of the first value
 * @param <G>
 *            specifies the type of the second value
 * @param <I>
 *            specifies the type of the third value
 * @param <K>
 *            specifies the type of the fourth value
 * @param <M>
 *            specifies the type of the fifth value
 * @param <O>
 *            specifies the type of the sixth value
 */
public class Tuple6<E, G, I, K, M, O> {

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
	 * Creates an Tuple6 with the passed values.
	 * 
	 * @param item1
	 *            the first value
	 * @param item2
	 *            the second value
	 * @param item3
	 *            the third value
	 * @param item4
	 *            the fourth value
	 * @param item5
	 *            the fifth value
	 * @param item6
	 *            the sixth value
	 */
	public Tuple6(E item1, G item2, I item3, K item4, M item5, O item6) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
	}

	/**
	 * Returns the first value.
	 * 
	 * @return the first value
	 */
	public E getItem1() {
		return item1;
	}

	/**
	 * Returns the second value.
	 * 
	 * @return the second value
	 */
	public G getItem2() {
		return item2;
	}

	/**
	 * Returns the third value.
	 * 
	 * @return the third value
	 */
	public I getItem3() {
		return item3;
	}

	/**
	 * Returns the fourth value.
	 * 
	 * @return the fourth value
	 */
	public K getItem4() {
		return item4;
	}

	/**
	 * Returns the fifth value.
	 * 
	 * @return the fifth value
	 */
	public M getItem5() {
		return item5;
	}

	/**
	 * Returns the sixth value.
	 * 
	 * @return the sixth value
	 */
	public O getItem6() {
		return item6;
	}

	/**
	 * Calculates a hash code based on each item this Tuple6 contains.
	 * 
	 * @return a hash code based on each item this Tuple6 contains
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item1 == null) ? 0 : item1.hashCode());
		result = prime * result + ((item2 == null) ? 0 : item2.hashCode());
		result = prime * result + ((item3 == null) ? 0 : item3.hashCode());
		result = prime * result + ((item4 == null) ? 0 : item4.hashCode());
		result = prime * result + ((item5 == null) ? 0 : item5.hashCode());
		result = prime * result + ((item6 == null) ? 0 : item6.hashCode());
		return result;
	}

	/**
	 * Compares this Tuple6 with the passed {@link Object}. Only an instance of
	 * Tuple6 can return true.
	 * 
	 * @param obj
	 * @return true if 'obj' is an instance of Tuple6 and all items 'equals'
	 *         return true, otherwise false
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
		Tuple6<?, ?, ?, ?, ?, ?> other = (Tuple6<?, ?, ?, ?, ?, ?>) obj;
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
		if (item4 == null) {
			if (other.item4 != null) {
				return false;
			}
		} else if (!item4.equals(other.item4)) {
			return false;
		}
		if (item5 == null) {
			if (other.item5 != null) {
				return false;
			}
		} else if (!item5.equals(other.item5)) {
			return false;
		}
		if (item6 == null) {
			if (other.item6 != null) {
				return false;
			}
		} else if (!item6.equals(other.item6)) {
			return false;
		}
		return true;
	}
}