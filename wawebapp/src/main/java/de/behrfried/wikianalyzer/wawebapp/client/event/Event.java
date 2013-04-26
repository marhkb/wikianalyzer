package de.behrfried.wikianalyzer.wawebapp.client.event;

import java.util.LinkedList;
import java.util.List;


public class Event<E extends EventArgs> {

	private List<Handler<E>> handlers = new LinkedList<Handler<E>>();
	private final Object initializationContext;
	
	public Event(final Object initializationContext) throws IllegalArgumentException {
		if(initializationContext == null) {
			throw new IllegalArgumentException("initializationContext == null");
		}
		this.initializationContext = initializationContext;
	}
	
	public void addHandler(Handler<E> handler) {
		this.handlers.add(handler);
	}
	
	public boolean removeHandler(Handler<E> handler) {
		return this.removeHandler(handler);
	}
	
	public void invoke(Object initializationContext, Object sender, E e) throws IllegalStateException {
		if(this.initializationContext != initializationContext) {
			throw new IllegalStateException("wrong initializationContext");
		}
		for(final Handler<E> h : this.handlers) {
			h.invoke(sender, e);
		}
	}
}
