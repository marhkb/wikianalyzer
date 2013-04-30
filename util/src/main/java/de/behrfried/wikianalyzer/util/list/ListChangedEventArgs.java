package de.behrfried.wikianalyzer.util.list;

import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.EventArgs;


public class ListChangedEventArgs<E> extends EventArgs {

	public enum ListChangedType {
		ADD,
		REMOVE,
		CLEAR
	}
	
	private final ListChangedType listChangedType;
	
	private final Tuple2<E, Integer> oldItem;
	private final Tuple2<E, Integer> newItem;
	
	public ListChangedEventArgs(ListChangedType listChangedType, final Tuple2<E, Integer> oldItem, Tuple2<E, Integer> newItem) {
		this.listChangedType = listChangedType;
		this.oldItem = oldItem;
		this.newItem = newItem;
	}
	
    public ListChangedType getListChangedType() {
    	return listChangedType;
    }
	
    public Tuple2<E, Integer> getOldItem() {
    	return oldItem;
    }
	
    public Tuple2<E, Integer> getNewItem() {
    	return newItem;
    }
}
