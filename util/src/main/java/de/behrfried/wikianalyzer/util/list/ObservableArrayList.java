package de.behrfried.wikianalyzer.util.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ObservableArrayList<E> extends DefaultObservableList<E> {

	public ObservableArrayList() {
    }

	public ObservableArrayList(Collection<E> collection) {
	    super(collection);
    }

	@Override
    protected List<E> createInternalList(Collection<E> collection) {
		if(collection == null) {
			return new ArrayList<E>();
		}
	    return new ArrayList<E>(collection);
    }

	@Override
    protected DefaultObservableList<E> createObservableList(Collection<E> collection) {
	    return new ObservableArrayList<E>(collection);
    }

}
