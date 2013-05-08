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

package de.behrfried.wikianalyzer.util.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of {@link DefaultObservableList} internally using an
 * {@link ArrayList}.
 * 
 * @author marcus
 * 
 * @param <E>
 *            specifies the type this {@link ObservableArrayList} shall hold
 */
public class ObservableArrayList<E> extends DefaultObservableList<E> {

	/**
	 * Creates an empty {@link ObservableArrayList}.
	 */
	public ObservableArrayList() {}

	/**
	 * Creates an {@link ObservableArrayList} based on the passed
	 * {@link Collection}.
	 * 
	 * @param collection
	 *            a {@link Collection} which elements shall be added to the
	 *            {@link ObservableArrayList}
	 */
	public ObservableArrayList(final Collection<E> collection) {
		super(collection);
	}

	/**
	 * Returns a new {@link ArrayList} based on the passed {@link Collection}.
	 * 
	 * @return a new {@link ArrayList} based on the passed {@link Collection}
	 */
	@Override
	protected List<E> createInternalList(final Collection<E> collection) {
		if(collection == null) {
			return new ArrayList<E>();
		}
		return new ArrayList<E>(collection);
	}

	/**
	 * Returns a new {@link DefaultObservableList} based on the passed
	 * {@link Collection}.
	 * 
	 * @return a new {@link DefaultObservableList} based on the passed
	 *         {@link Collection}
	 */
	@Override
	protected DefaultObservableList<E> createObservableList(final Collection<E> collection) {
		return new ObservableArrayList<E>(collection);
	}

}
