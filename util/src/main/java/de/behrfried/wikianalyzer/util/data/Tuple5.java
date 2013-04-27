package de.behrfried.wikianalyzer.util.data;

public class Tuple5<E, G, I, K, M> {

	private final E item1;
	private final G item2;
	private final I item3;
	private final K item4;
	private final M item5;

	public Tuple5(E item1, G item2, I item3, K item4, M item5) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
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

	public M getItem5() {
		return item5;
	}
	
}

