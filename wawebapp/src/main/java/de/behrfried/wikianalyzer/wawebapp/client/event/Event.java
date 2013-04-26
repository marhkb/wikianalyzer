package de.behrfried.wikianalyzer.wawebapp.client.event;

import java.util.LinkedList;
import java.util.List;


public class Event<E extends EventArgs> {

	private List<Handler<E>> handlers = new LinkedList<Handler<E>>();
	
	public void addHandler(Handler<E> handler) {
		this.handlers.add(handler);
	}
	
	public boolean removeHandler(Handler<E> handler) {
		return this.removeHandler(handler);
	}
	
	public void invoke(Object sender, E e) {
		for(final Handler<E> h : this.handlers) {
			h.invoke(sender, e);
		}
	}
}
