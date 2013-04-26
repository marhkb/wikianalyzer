package de.behrfried.wikianalyzer.wawebapp.client.event;


public interface Handler<E extends EventArgs> {

	public void invoke(Object sender, E e);
}
