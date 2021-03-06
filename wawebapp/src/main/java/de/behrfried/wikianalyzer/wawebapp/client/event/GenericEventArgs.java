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

package de.behrfried.wikianalyzer.wawebapp.client.event;


import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;

/**
 * An generic implementation of {@link EventArgs}
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the enclosed type which the GenericEventArgs holds
 */
public class GenericEventArgs<E> extends EventArgs {

	/**
	 * the enclosed type to hold by this GenericEventArgs
	 */
	private final E value;

	/**
	 * Created an GenericEventArgs.
	 * 
	 * @param value
	 *            the enclosed type to hold by this GenericEventArgs
	 */
	public GenericEventArgs(final E value) {
		this.value = value;
	}

	/**
	 * Returns the enclosed type of by this GenericEventArgs.
	 * 
	 * @return the enclosed type of by this GenericEventArgs
	 */
	public E getValue() {
		return this.value;
	}
}
