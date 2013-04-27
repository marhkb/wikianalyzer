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

package de.behrfried.wikianalyzer.util.event;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of Microsoft's Event Pattern in Java.
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the type that is passed to the callback methods
 */
public class Event<E extends EventArgs> {

	/**
	 * List containing all handlers
	 */
	private final List<Handler<E>> handlers = new LinkedList<Handler<E>>();

	/**
	 * The initializationContext is passed at creating time to ensure the
	 * {@link Event} only can be fired within its initialization class. the The
	 * initializationContext is checked has to be passed as an argument to
	 * 'fire' method.
	 */
	private final Object initializationContext;

	/**
	 * Creates an Event with the passed initializationContext
	 * 
	 * @param initializationContext
	 *            any {@link Object} to ensure that only the initializing class
	 *            can fire this Event
	 * @throws IllegalArgumentException
	 *             if initializationContext == null
	 */
	public Event(final Object initializationContext)
			throws IllegalArgumentException {
		if (initializationContext == null) {
			throw new IllegalArgumentException("initializationContext == null");
		}
		this.initializationContext = initializationContext;
	}

	/**
	 * Adds the passed {@link Handler} to this Event. It will be invoked on
	 * 'fire' method.
	 * 
	 * @param handler
	 * @throws IllegalArgumentException
	 */
	public void addHandler(final Handler<E> handler)
			throws IllegalArgumentException {
		this.handlers.add(handler);
	}

	/**
	 * Removes the passed {@link Handler} from this Event. If the handler is
	 * linked with this Event it will be removed and won't be invoked any more
	 * on 'fire'.
	 * 
	 * @param handler
	 *            the Handler to be removed from this Event
	 * @return true if the passed Handler was linked with this Event and could
	 *         have been removed
	 * @throws IllegalArgumentException
	 *             if handler == null
	 */
	public boolean removeHandler(final Handler<E> handler)
			throws IllegalArgumentException {
		if (handler == null) {
			throw new IllegalArgumentException("handler == null");
		}
		return this.removeHandler(handler);
	}

	/**
	 * Fires this Event by invoking all associated {@link Handler}'s.
	 * 
	 * @param initializationContext
	 *            this should be the same object as passed to constructor at
	 *            Event's instantiation
	 * @param sender
	 *            the {@link Object} that fires this Event
	 * @param e
	 *            an instance of {@link EventArgs} containing several
	 *            information about the fired Event
	 * @throws IllegalStateException
	 *             if the passed initializationContext is not the same object as
	 *             passed at construction of this Event
	 */
	public void fire(final Object initializationContext, final Object sender,
			final E e) throws IllegalStateException {
		if (this.initializationContext != initializationContext) {
			throw new IllegalStateException("wrong initializationContext");
		}
		for (final Handler<E> h : this.handlers) {
			h.invoke(sender, e);
		}
	}
}
