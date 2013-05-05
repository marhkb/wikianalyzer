/**
 * 
 */
package de.behrfried.wikianalyzer.util.list;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * @author marcus
 *
 */
public class ObservableLinkedList<E> extends DefaultObservableList<E> {
	
	public ObservableLinkedList() {
    }

	public ObservableLinkedList(Collection<E> collection) {
	    super(collection);
    }

	@Override
    protected List<E> createInternalList(Collection<E> collection) {
	    if(collection == null) {
	    	return new LinkedList<E>();
	    }
	    return new LinkedList<E>(collection);
    }

	@Override
    protected DefaultObservableList<E> createObservableList(Collection<E> collection) {
	    return new ObservableLinkedList<E>(collection);
    }

}
