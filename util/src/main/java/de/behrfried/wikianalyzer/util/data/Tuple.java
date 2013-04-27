/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package de.behrfried.wikianalyzer.util.data;

/**
 * Class for creating Tuples.
 * 
 * @see Tuple2
 * @see Tuple3
 * @see Tuple4
 * @see Tuple5
 * @see Tuple6
 * @see Tuple7
 * @see Tuple8
 * @author marcus
 * 
 */
public class Tuple {

	/**
	 * 
	 */
	private Tuple() {
	}

	/**
	 * Creates an instance of {@link Tuple2} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple2 to be constructed
	 * @param item2
	 *            the second item of the Tuple2 to be constructed
	 * @return an instance of {@link Tuple2} constructed the passed arguments
	 */
	public static <E, G> Tuple2<E, G> create(E item1, G item2) {
		return new Tuple2<E, G>(item1, item2);
	}

	/**
	 * 
	 * Creates an instance of {@link Tuple3} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple3 to be constructed
	 * @param item2
	 *            the second item of the Tuple3 to be constructed
	 * @param item3
	 *            the third item of the Tuple3 to be constructed
	 * @return an instance of {@link Tuple3} constructed the passed arguments
	 */
	public static <E, G, I> Tuple3<E, G, I> create(E item1, G item2, I item3) {
		return new Tuple3<E, G, I>(item1, item2, item3);
	}

	/**
	 * Creates an instance of {@link Tuple4} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple4 to be constructed
	 * @param item2
	 *            the second item of the Tuple4 to be constructed
	 * @param item3
	 *            the third item of the Tuple4 to be constructed
	 * @param item4
	 *            the fourth item of the Tuple4 to be constructed
	 * @return an instance of {@link Tuple4} constructed the passed arguments
	 */
	public static <E, G, I, K> Tuple4<E, G, I, K> create(E item1, G item2,
			I item3, K item4) {
		return new Tuple4<E, G, I, K>(item1, item2, item3, item4);
	}

	/**
	 * Creates an instance of {@link Tuple5} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple5 to be constructed
	 * @param item2
	 *            the second item of the Tuple5 to be constructed
	 * @param item3
	 *            the third item of the Tuple5 to be constructed
	 * @param item4
	 *            the fourth item of the Tuple5 to be constructed
	 * @param item5
	 *            the fifth item of the Tuple5 to be constructed
	 * @return an instance of {@link Tuple5} constructed the passed arguments
	 */
	public static <E, G, I, K, M> Tuple5<E, G, I, K, M> create(E item1,
			G item2, I item3, K item4, M item5) {
		return new Tuple5<E, G, I, K, M>(item1, item2, item3, item4, item5);
	}

	/**
	 * Creates an instance of {@link Tuple6} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple6 to be constructed
	 * @param item2
	 *            the second item of the Tuple6 to be constructed
	 * @param item3
	 *            the third item of the Tuple6 to be constructed
	 * @param item4
	 *            the fourth item of the Tuple6 to be constructed
	 * @param item5
	 *            the fifth item of the Tuple6 to be constructed
	 * @param item6
	 *            the sixth item of the Tuple6 to be constructed
	 * @return an instance of {@link Tuple6} constructed the passed arguments
	 */
	public static <E, G, I, K, M, O> Tuple6<E, G, I, K, M, O> create(E item1,
			G item2, I item3, K item4, M item5, O item6) {
		return new Tuple6<E, G, I, K, M, O>(item1, item2, item3, item4, item5,
				item6);
	}

	/**
	 * Creates an instance of {@link Tuple7} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple7 to be constructed
	 * @param item2
	 *            the second item of the Tuple7 to be constructed
	 * @param item3
	 *            the third item of the Tuple7 to be constructed
	 * @param item4
	 *            the fourth item of the Tuple7 to be constructed
	 * @param item5
	 *            the fifth item of the Tuple7 to be constructed
	 * @param item6
	 *            the sixth item of the Tuple7 to be constructed
	 * @param item7
	 *            the seventh item of the Tuple7 to be constructed
	 * @return an instance of {@link Tuple7} constructed the passed arguments
	 */
	public static <E, G, I, K, M, O, Q> Tuple7<E, G, I, K, M, O, Q> create(
			E item1, G item2, I item3, K item4, M item5, O item6, Q item7) {
		return new Tuple7<E, G, I, K, M, O, Q>(item1, item2, item3, item4,
				item5, item6, item7);
	}

	/**
	 * Creates an instance of {@link Tuple8} with the passed arguments.
	 * 
	 * @param item1
	 *            the first item of the Tuple8 to be constructed
	 * @param item2
	 *            the second item of the Tuple8 to be constructed
	 * @param item3
	 *            the third item of the Tuple8 to be constructed
	 * @param item4
	 *            the fourth item of the Tuple8 to be constructed
	 * @param item5
	 *            the fifth item of the Tuple8 to be constructed
	 * @param item6
	 *            the sixth item of the Tuple8 to be constructed
	 * @param item7
	 *            the sixth item of the Tuple8 to be constructed
	 * @param item8
	 *            the eighth item of the Tuple8 to be constructed
	 * @return an instance of {@link Tuple8} constructed the passed arguments
	 */
	public static <E, G, I, K, M, O, Q, S> Tuple8<E, G, I, K, M, O, Q, S> create(
			E item1, G item2, I item3, K item4, M item5, O item6, Q item7,
			S item8) {
		return new Tuple8<E, G, I, K, M, O, Q, S>(item1, item2, item3, item4,
				item5, item6, item7, item8);
	}
}
