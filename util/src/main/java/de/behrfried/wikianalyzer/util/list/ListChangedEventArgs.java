package de.behrfried.wikianalyzer.util.list;

import java.util.Collections;
import java.util.List;
import de.behrfried.wikianalyzer.util.event.EventArgs;


public class ListChangedEventArgs<E> extends EventArgs {

	public enum ListChangedType {
		ADD_REMOVE,
		CLEAR
	}
	
	private final ListChangedType listChangedType;
	
	private final List<E> oldItems;
	private final List<E> newItems;
	
	public ListChangedEventArgs(ListChangedType listChangedType, final List<E> oldItems, List<E> newItems) {
		this.listChangedType = listChangedType;
		this.oldItems = oldItems;
		this.newItems = newItems;
	}
	
    public ListChangedType getListChangedType() {
    	return listChangedType;
    }
	
    public List<E> getOldItems() {
    	return Collections.unmodifiableList(this.oldItems);
    }
	
    public List<E> getNewItems() {
    	return Collections.unmodifiableList(this.newItems);
    }
}
