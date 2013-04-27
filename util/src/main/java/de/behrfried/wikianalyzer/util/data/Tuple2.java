package de.behrfried.wikianalyzer.util.data;

public class Tuple2<E, G> {

	private final E item1;
	private final G item2;
	
	public Tuple2(E item1, G item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public E getItem1() {
		return item1;
	}

	public G getItem2() {
		return item2;
	}
}