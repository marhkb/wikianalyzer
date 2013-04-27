package de.behrfried.wikianalyzer.util.data;

public class Tuple4<E, G, I, K> {

	private final E item1;
	private final G item2;
	private final I item3;
	private final K item4;

	public Tuple4(E item1, G item2, I item3, K item4) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
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

	public K getItem4() {
		return item4;
	}


}
