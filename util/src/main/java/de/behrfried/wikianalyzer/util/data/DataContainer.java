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
 * A simple class that stores an {@link Object}. This class can be used to pass
 * an argument into an anonymous inner class and change the Object's value. Thus
 * the DataContainer has to be final but its value is still mutable.
 * 
 * @author marcus
 * 
 * @param <E> specifies the type this DataContainer stores
 */
public class DataContainer<E> {

	/**
	 * the stored Object
	 */
	private E value;

	/**
	 * Creates an DataContainer with the passed argument.
	 * @param value the Object to be stored by this DataContainer
	 */
	public DataContainer(final E value) {
		this.value = value;
	}

	/**
	 * Returns the stored Object.
	 * @return the stored Object
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Sets the stored Object.
	 * @param value the new Object to be stored
	 */
	public void setValue(E value) {
		this.value = value;
	}
}
