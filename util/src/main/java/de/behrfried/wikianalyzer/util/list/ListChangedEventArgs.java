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

import java.util.Collections;
import java.util.List;
import de.behrfried.wikianalyzer.util.event.EventArgs;

/**
 * {@link EventArgs} that is forwarded when a {@link ObservableList} has
 * changed.
 * 
 * @author marcus
 * 
 * @param <E>
 */
public class ListChangedEventArgs<E> extends EventArgs {

	/**
	 * Enum determining the specific change action of a
	 * {@link ListChangedEventArgs}.
	 * 
	 * @author marcus
	 * 
	 */
	public enum ListChangedAction {

		/**
		 * Elements has been added and/or removed from the
		 * {@link ObservableList}.
		 */
		ADD_REMOVE,

		/**
		 * The {@link ObservableList} has been cleared.
		 */
		CLEAR,
	}

	/**
	 * determines what has happened to the list
	 */
	private final ListChangedAction listChangedAction;

	/**
	 * list containing the removed elements
	 */
	private final List<E> oldItems;

	/**
	 * list containing the added elements.
	 */
	private final List<E> newItems;

	/**
	 * Creates a {@link ListChangedEventArgs}.
	 * 
	 * @param listChangedType
	 *            the {@link ListChangedAction}
	 * @param oldItems
	 *            a {@link List} containing the removed elements
	 * @param newItems
	 *            a {@link List} containing the added elements
	 */
	public ListChangedEventArgs(final ListChangedAction listChangedType, final List<E> oldItems, final List<E> newItems) {
		this.listChangedAction = listChangedType;
		this.oldItems = oldItems;
		this.newItems = newItems;
	}

	/**
	 * Returns a {@link ListChangedAction} that determines what actions has been
	 * performed on the {@link ObservableList}.
	 * 
	 * @return a {@link ListChangedAction} that determines what actions has been
	 *         performed on the {@link ObservableList}
	 */
	public ListChangedAction getListChangedAction() {
		return this.listChangedAction;
	}

	/**
	 * Returns a list containing the removed elements or {@code null}.
	 * 
	 * @return if {@code getListChangedAction() != ListChangedAction.CLEAR } and
	 *         elements has been removed -> a list containing the removed
	 *         elements, otherwise {@code null}
	 */
	public List<E> getOldItems() {
		if(this.oldItems == null) {
			return null;
		}
		return Collections.unmodifiableList(this.oldItems);
	}

	/**
	 * Returns a list containing the new elements or {@code null}.
	 * 
	 * @return if {@code getListChangedAction() != ListChangedAction.CLEAR } and
	 *         elements has been added -> a list containing the added elements,
	 *         otherwise {@code null}
	 */
	public List<E> getNewItems() {
		if(this.newItems == null) {
			return null;
		}
		return Collections.unmodifiableList(this.newItems);
	}
}
