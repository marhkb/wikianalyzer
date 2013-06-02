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

/**
 * 
 */
package de.behrfried.wikianalyzer.wawebapp.client.util.list;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of {@link DefaultObservableList} internally using an
 * {@link java.util.LinkedList}.
 *
 * @author marcus
 *
 * @param <E>
 *            specifies the type this {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList} shall hold
 */
public class ObservableLinkedList<E> extends DefaultObservableList<E> {

	/**
	 * Creates an empty {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList}.
	 */
	public ObservableLinkedList() {}

	/**
	 * Creates an {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList} based on the passed
	 * {@link java.util.Collection}.
	 *
	 * @param collection
	 *            a {@link java.util.Collection} which elements shall be added to the
	 *            {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList}
	 */
	public ObservableLinkedList(final Collection<E> collection) {
		super(collection);
	}

	/**
	 * Returns a new {@link linkedList} based on the passed {@link java.util.Collection}.
	 *
	 * @return a new {@link java.util.LinkedList} based on the passed {@link java.util.Collection}
	 */
	@Override
	protected List<E> createInternalList(final Collection<E> collection) {
		if(collection == null) {
			return new LinkedList<E>();
		}
		return new LinkedList<E>(collection);
	}

	/**
	 * Returns a new {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList} based on the passed
	 * {@link java.util.Collection}.
	 *
	 * @return a new {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableLinkedList} based on the passed
	 *         {@link java.util.Collection}
	 */
	@Override
	protected DefaultObservableList<E> createObservableList(final Collection<E> collection) {
		return new ObservableLinkedList<E>(collection);
	}

}
