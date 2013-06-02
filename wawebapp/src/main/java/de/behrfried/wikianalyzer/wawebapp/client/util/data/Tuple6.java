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

package de.behrfried.wikianalyzer.wawebapp.client.util.data;

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
	public Tuple6(final E item1, final G item2, final I item3, final K item4, final M item5, final O item6) {
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
		return this.item1;
	}

	/**
	 * Returns the second value.
	 * 
	 * @return the second value
	 */
	public G getItem2() {
		return this.item2;
	}

	/**
	 * Returns the third value.
	 * 
	 * @return the third value
	 */
	public I getItem3() {
		return this.item3;
	}

	/**
	 * Returns the fourth value.
	 * 
	 * @return the fourth value
	 */
	public K getItem4() {
		return this.item4;
	}

	/**
	 * Returns the fifth value.
	 * 
	 * @return the fifth value
	 */
	public M getItem5() {
		return this.item5;
	}

	/**
	 * Returns the sixth value.
	 * 
	 * @return the sixth value
	 */
	public O getItem6() {
		return this.item6;
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
		result = prime * result + (this.item1 == null ? 0 : this.item1.hashCode());
		result = prime * result + (this.item2 == null ? 0 : this.item2.hashCode());
		result = prime * result + (this.item3 == null ? 0 : this.item3.hashCode());
		result = prime * result + (this.item4 == null ? 0 : this.item4.hashCode());
		result = prime * result + (this.item5 == null ? 0 : this.item5.hashCode());
		result = prime * result + (this.item6 == null ? 0 : this.item6.hashCode());
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
	public boolean equals(final Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		final Tuple6<?, ?, ?, ?, ?, ?> other = (Tuple6<?, ?, ?, ?, ?, ?>)obj;
		if(this.item1 == null) {
			if(other.item1 != null) {
				return false;
			}
		} else if(!this.item1.equals(other.item1)) {
			return false;
		}
		if(this.item2 == null) {
			if(other.item2 != null) {
				return false;
			}
		} else if(!this.item2.equals(other.item2)) {
			return false;
		}
		if(this.item3 == null) {
			if(other.item3 != null) {
				return false;
			}
		} else if(!this.item3.equals(other.item3)) {
			return false;
		}
		if(this.item4 == null) {
			if(other.item4 != null) {
				return false;
			}
		} else if(!this.item4.equals(other.item4)) {
			return false;
		}
		if(this.item5 == null) {
			if(other.item5 != null) {
				return false;
			}
		} else if(!this.item5.equals(other.item5)) {
			return false;
		}
		if(this.item6 == null) {
			if(other.item6 != null) {
				return false;
			}
		} else if(!this.item6.equals(other.item6)) {
			return false;
		}
		return true;
	}
}
