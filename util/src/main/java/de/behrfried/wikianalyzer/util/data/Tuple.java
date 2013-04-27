package de.behrfried.wikianalyzer.util.data;

public class Tuple {
	
	private Tuple() {}

	public static <E, G> Tuple2<E, G> create(E item1, G item2) {
		return new Tuple2<E, G>(item1,item2);
	}
	
	public static <E, G, I> Tuple3<E, G, I> create(E item1, G item2, I item3) {
		return new Tuple3<E, G, I>(item1,item2, item3);
	}
	
	public static <E, G, I, K> Tuple4<E, G, I, K> create(E item1, G item2, I item3, K item4) {
		return new Tuple4<E, G, I, K>(item1,item2, item3, item4);
	}
	
	public static <E, G, I, K, M> Tuple5<E, G, I, K, M> create(E item1, G item2, I item3, K item4, M item5) {
		return new Tuple5<E, G, I, K, M>(item1,item2, item3, item4, item5);
	}
	
	public static <E, G, I, K, M, O> Tuple6<E, G, I, K, M, O> create(E item1, G item2, I item3, K item4, M item5, O item6) {
		return new Tuple6<E, G, I, K, M, O>(item1,item2, item3, item4, item5, item6);
	}
	
	public static <E, G, I, K, M, O, Q> Tuple7<E, G, I, K, M, O, Q> create(E item1, G item2, I item3, K item4, M item5, O item6, Q item7) {
		return new Tuple7<E, G, I, K, M, O, Q>(item1,item2, item3, item4, item5, item6, item7);
	}
	
	public static <E, G, I, K, M, O, Q, S> Tuple8<E, G, I, K, M, O, Q, S> create(E item1, G item2, I item3, K item4, M item5, O item6, Q item7, S item8) {
		return new Tuple8<E, G, I, K, M, O, Q, S>(item1,item2, item3, item4, item5, item6, item7, item8);
	}
}
