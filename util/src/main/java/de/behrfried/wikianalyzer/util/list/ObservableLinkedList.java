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
package de.behrfried.wikianalyzer.util.list;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of {@link DefaultObservableList} internally using an
 * {@link LinkedList}.
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the type this {@link ObservableLinkedList} shall hold
 */
public class ObservableLinkedList<E> extends DefaultObservableList<E> {

	/**
	 * Creates an empty {@link ObservableLinkedList}.
	 */
	public ObservableLinkedList() {}

	/**
	 * Creates an {@link ObservableLinkedList} based on the passed
	 * {@link Collection}.
	 * 
	 * @param collection
	 *            a {@link Collection} which elements shall be added to the
	 *            {@link ObservableLinkedList}
	 */
	public ObservableLinkedList(final Collection<E> collection) {
		super(collection);
	}

	/**
	 * Returns a new {@link linkedList} based on the passed {@link Collection}.
	 * 
	 * @return a new {@link LinkedList} based on the passed {@link Collection}
	 */
	@Override
	protected List<E> createInternalList(final Collection<E> collection) {
		if(collection == null) {
			return new LinkedList<E>();
		}
		return new LinkedList<E>(collection);
	}

	/**
	 * Returns a new {@link ObservableLinkedList} based on the passed
	 * {@link Collection}.
	 * 
	 * @return a new {@link ObservableLinkedList} based on the passed
	 *         {@link Collection}
	 */
	@Override
	protected DefaultObservableList<E> createObservableList(final Collection<E> collection) {
		return new ObservableLinkedList<E>(collection);
	}

}
