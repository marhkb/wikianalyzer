package de.behrfried.wikianalyzer.util.data;

public class Tuple3<E, G, I> {

	private final E item1;
	private final G item2;
	private final I item3;
	
	public Tuple3(E item1, G item2, I item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	public E getItem1() {
		return item1;
	}

	public G getItem2() {
		return item2;
	}

	public I getItem3() {
		return item3;
	}
	
	
}

