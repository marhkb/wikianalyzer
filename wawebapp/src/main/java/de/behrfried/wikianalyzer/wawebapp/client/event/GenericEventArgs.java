package de.behrfried.wikianalyzer.wawebapp.client.event;

public class GenericEventArgs<E> extends EventArgs {
	private final E value;

	public GenericEventArgs(E value) {
		this.value = value;
	}

	public E getValue() {
		return value;
	}
}
