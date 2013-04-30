package de.behrfried.wikianalyzer.util.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import de.behrfried.wikianalyzer.util.event.Event;


public final class DefaultObservableList<E> implements ObservableList<E> {

	private final Object initContext = new Object();
	
	private final Event<ListChangedEventArgs<E>> listChanged = new Event<ListChangedEventArgs<E>>(this.initContext);
	public Event<ListChangedEventArgs<E>> listChanged() {
	    return this.listChanged;
    }
	public boolean add(E e) {
	    // TODO Auto-generated method stub
	    return false;
    }
	public void add(int index, E element) {
	    // TODO Auto-generated method stub
	    
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
	    // TODO Auto-generated method stub
	    return null;
    }
	public int size() {
	    // TODO Auto-generated method stub
	    return 0;
    }
	public List<E> subList(int fromIndex, int toIndex) {
	    // TODO Auto-generated method stub
	    return null;
    }
	public Object[] toArray() {
	    // TODO Auto-generated method stub
	    return null;
    }
	public <T> T[] toArray(T[] a) {
	    // TODO Auto-generated method stub
	    return null;
    }
}
