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

/**
 * An Delegate Interface which is mainly used in cooperation with {@link Event}
 * s.
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the type to be passed in Handler's 'invoke' method
 */
public interface Handler<E extends EventArgs> {

	/**
	 * This method is supposed to be overridden by concrete Handlers.
	 * 
	 * @param sender
	 *            specifies the {@link Object} in which the Handler has been
	 *            raised
	 * @param e
	 *            An type of {@link EventArgs} that holds additional information
	 * @throws IllegalArgumentException
	 *             if object == null || e == null
	 */
	public void invoke(Object sender, E e) throws IllegalArgumentException;
}
