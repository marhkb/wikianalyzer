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
	 * A class that holds an {@link Handler} and can remove it.
	 * 
	 * @author marcus
	 * 
	 */
	public final class EventHandlerRegistration {

		/**
		 * the encapsulated {@link Handler}
		 */
		private final Handler<E> handler;

		/**
		 * Creates an EventHandlerRegistration with the passed {@link Handler}
		 * 
		 * @param handler
		 *            the {@link Handler} to be held
		 */
		private EventHandlerRegistration(final Handler<E> handler) {
			this.handler = handler;
		}

		/**
		 * Returns the encapsulated {@link Handler}.
		 * 
		 * @return the encapsulated {@link Handler}
		 */
		public Handler<E> getHandler() {
			return this.handler;
		}

		/**
		 * Removes the encapsulated {@link Handler} from the {@link Event}
		 * 
		 * @return true whether the {@link Handler} could have been removed
		 */
		public boolean removeHandler() {
			return Event.this.removeHandler(this.handler);
		}
	}

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
	private final Object initContext;

	/**
	 * Creates an Event with the passed initializationContext
	 * 
	 * @param initContext
	 *            any {@link Object} to ensure that only the initializing class
	 *            can fire this Event
	 * @throws IllegalArgumentException
	 *             if initContext == null
	 */
	public Event(final Object initContext) throws IllegalArgumentException {
		if(initContext == null) {
			throw new IllegalArgumentException("initContext == null");
		}
		this.initContext = initContext;
	}

	/**
	 * Adds the passed {@link Handler} to this Event. It will be invoked on
	 * 'fire' method.
	 * 
	 * @param handler
	 * @throws IllegalArgumentException
	 */
	public EventHandlerRegistration addHandler(final Handler<E> handler) throws IllegalArgumentException {
		if(handler == null) {
			throw new IllegalArgumentException("handler == null");
		}
		this.handlers.add(handler);
		return new EventHandlerRegistration(handler);
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
	public boolean removeHandler(final Handler<E> handler) throws IllegalArgumentException {
		if(handler == null) {
			throw new IllegalArgumentException("handler == null");
		}
		return this.handlers.remove(handler);
	}

	/**
	 * Checks whether an passed {@link Handler} is linked with this Event.
	 * 
	 * @param handler
	 *            the Handler to be checked
	 * @return 'true' if the passed Handler is linked with this Event
	 */
	public boolean containsHandler(final Handler<E> handler) {
		return this.handlers.contains(handler);
	}

	/**
	 * Returns the number of Handlers linked with this Event.
	 * 
	 * @return the number of Handlers linked with this Event
	 */
	public int size() {
		return this.handlers.size();
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
	public void fire(final Object initializationContext, final Object sender, final E e) throws IllegalStateException {
		if(this.initContext != initializationContext) {
			throw new IllegalStateException("wrong initContext");
		}
		for(final Handler<E> h : this.handlers) {
			h.invoke(sender, e);
		}
	}
}
