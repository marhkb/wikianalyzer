package de.behrfried.wikianalyzer.util.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs.ListChangedType;


public final class DefaultObservableList<E> implements ObservableList<E> {

	private final Object initContext = new Object();
	
	private final Event<ListChangedEventArgs<E>> listChanged = new Event<ListChangedEventArgs<E>>(this.initContext);
	
	private final List<E> internalList;
	
	public DefaultObservableList(final List<E> internalList) {
		this.internalList = internalList;
	}
	
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
	    // TODO Auto-generated method stub
	    return false;
    }
	public boolean addAll(int index, Collection<? extends E> c) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public void clear() {
	    // TODO Auto-generated method stub
	    
    }
	public boolean contains(Object o) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public boolean containsAll(Collection<?> c) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public E get(int index) {
	    // TODO Auto-generated method stub
	    return null;
    }
	public int indexOf(Object o) {
	    // TODO Auto-generated method stub
	    return 0;
    }
	public boolean isEmpty() {
	    // TODO Auto-generated method stub
	    return false;
    }
	public Iterator<E> iterator() {
	    // TODO Auto-generated method stub
	    return null;
    }
	public int lastIndexOf(Object o) {
	    // TODO Auto-generated method stub
	    return 0;
    }
	public ListIterator<E> listIterator() {
	    // TODO Auto-generated method stub
	    return null;
    }
	public ListIterator<E> listIterator(int index) {
	    // TODO Auto-generated method stub
	    return null;
    }
	public boolean remove(Object o) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public E remove(int index) {
	    // TODO Auto-generated method stub
	    return null;
    }
	public boolean removeAll(Collection<?> c) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public boolean retainAll(Collection<?> c) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public E set(int index, E element) {
	    final E result = this.internalList.set(index, element);
	    return result;
    }
	public int size() {
	    return this.internalList.size();
    }
	public List<E> subList(int fromIndex, int toIndex) {
	    return new DefaultObservableList<E>(this.internalList.subList(fromIndex, toIndex));
    }
	public Object[] toArray() {
	    return this.internalList.toArray();
    }
	public <T> T[] toArray(T[] a) {
	    return this.internalList.toArray(a);
    }
}
