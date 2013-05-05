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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs.ListChangedType;

public abstract class DefaultObservableList<E> implements ObservableList<E> {

	private final Object initContext = new Object();

	private final Event<ListChangedEventArgs<E>> listChanged = new Event<ListChangedEventArgs<E>>(this.initContext);

	private final List<E> internalList;

	public DefaultObservableList() {
		this.internalList = this.createInternalList(null);
	}
	
	public DefaultObservableList(Collection<E> collection) {
		this.internalList = this.createInternalList(collection);
	}
	
	protected abstract List<E> createInternalList(Collection<E> collection);
	
	protected abstract DefaultObservableList<E> createObservableList(Collection<E> collection);
	

	public Event<ListChangedEventArgs<E>> listChanged() {
		return this.listChanged;
	}

	public boolean add(E e) {
		final boolean result = this.internalList.add(e);
		if(result) {
			final List<E> newItems = new LinkedList<E>();
			newItems.add(e);
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, null, newItems));
		}
		return result;
	}

	public void add(int index, E element) {
		this.internalList.add(index, element);
		final List<E> newItems = new LinkedList<E>();
		newItems.add(element);
		this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, null, newItems));
	}

	public boolean addAll(Collection<? extends E> c) {
		final boolean result = this.internalList.addAll(c);
		if(result) {
			final List<E> newItems = new LinkedList<E>();
			for(final E e : c) {
				newItems.add(e);
			}
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, null, newItems));
		}
		return result;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		final boolean result = this.internalList.addAll(index, c);
		if(result) {
			final List<E> newItems = new LinkedList<E>();
			for(final E e : c) {
				newItems.add(e);
			}
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, null, newItems));
		}
		return result;
	}

	public void clear() {
		this.internalList.clear();
		this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.CLEAR, null, null));
	}

	public boolean contains(Object o) {
		return this.internalList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.internalList.containsAll(c);
	}

	public E get(int index) {
		return this.internalList.get(index);
	}

	public int indexOf(Object o) {
		return this.internalList.indexOf(o);
	}

	public boolean isEmpty() {
		return this.internalList.isEmpty();
	}

	public Iterator<E> iterator() {
		return this.internalList.iterator();
	}

	public int lastIndexOf(Object o) {
		return this.internalList.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return this.internalList.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return this.internalList.listIterator(index);
	}

	@SuppressWarnings("unchecked")
    public boolean remove(Object o) {
		final boolean result = this.internalList.remove(o);
		if(result) {
			final List<E> oldItems = new LinkedList<E>();
			oldItems.add((E)o);
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, oldItems, null));
		}
		return result;
	}

	public E remove(int index) {
		final E e = this.internalList.remove(index);
		final List<E> oldItems = new LinkedList<E>();
		oldItems.add(e);
		this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, oldItems, null));
		return e;
	}

	@SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
		final List<E> oldItems = new LinkedList<E>();
		for(final Object e : c) {
			if(this.contains(e)) {
				oldItems.add((E)e);
			}
		}
		final boolean result = this.internalList.removeAll(c);
		if(result) {
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, oldItems, null));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
		final List<E> oldItems = new LinkedList<E>();
		for(final Object e : this.internalList) {
			if(!c.contains(e)) {
				oldItems.add((E)e);
			}
		}
		final boolean result = this.internalList.retainAll(c);
		if(result) {
			this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, oldItems, null));
		}
		return result;
	}

	public E set(int index, E element) {
		final E result = this.internalList.set(index, element);
		final List<E> oldItems = new LinkedList<E>();
		final List<E> newItems = new LinkedList<E>();
		oldItems.add(result);
		newItems.add(element);
		this.listChanged().fire(this.initContext, this, new ListChangedEventArgs<E>(ListChangedType.ADD_REMOVE, oldItems, newItems));
		return result;
	}

	public int size() {
		return this.internalList.size();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return this.createObservableList(this.internalList.subList(fromIndex, toIndex));
	}

	public Object[] toArray() {
		return this.internalList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return this.internalList.toArray(a);
	}
}
