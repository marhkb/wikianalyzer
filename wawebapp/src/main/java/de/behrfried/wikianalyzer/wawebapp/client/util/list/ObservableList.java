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

package de.behrfried.wikianalyzer.wawebapp.client.util.list;

import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;

import java.util.List;

/**
 * An interface for observable {@link java.util.List}s.
 *
 * @author marcus
 *
 * @param <E>
 *            specifies the type this {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.DefaultObservableList} shall hold
 */
public interface ObservableList<E> extends List<E> {

	/**
	 * Returns an {@link de.behrfried.wikianalyzer.wawebapp.client.util.event.Event} that is raised when elements of the
	 * {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableList} has been added, removed, or replaced or the
	 * {@link de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableList} has been cleared.
	 *
	 * @return an {@link de.behrfried.wikianalyzer.wawebapp.client.util.event.Event} that is raised when the List has changed
	 */
	Event<ListChangedEventArgs<E>> listChanged();
}
