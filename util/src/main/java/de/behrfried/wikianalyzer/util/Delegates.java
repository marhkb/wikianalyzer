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
package de.behrfried.wikianalyzer.util;


/**
 * Class containing various Delegate Interfaces.
 * 
 * @author marcus
 * 
 */
public final class Delegates {

	/**
	 * Private constructor to prevent initialization.
	 */
	private Delegates() {}

	/**
	 * A simple Delegate Interface for methods without a return type and without
	 * arguments.
	 * 
	 * @author marcus
	 * 
	 */
	public interface Action {

		public void invoke();
	}

	/**
	 * A simple Delegate Interface for methods without a return type and with
	 * one argument.
	 * 
	 * @author marcus
	 * 
	 * @param <E1>
	 *            the type to be passed as an argument into the Delegate
	 */
	public interface Action1<E1> {

		public void invoke(E1 e1);
	}

	/**
	 * A simple Delegate Interface for methods with a return type and without
	 * arguments.
	 * 
	 * @author marcus
	 * 
	 * @param <EResult>
	 *            the type to be returned by the Delegate
	 */
	public interface Func<EResult> {

		public EResult invoke();
	}

	/**
	 * A simple Delegate Interface for methods with a return type and with one
	 * argument.
	 * 
	 * @author marcus
	 * 
	 * @param <E1>
	 *            the type to be passed as an argument into the Delegate
	 * @param <EResult>
	 *            the type to be returned by the Delegate
	 */
	public interface Func1<E1, EResult> {

		public EResult invoke(E1 e1);
	}
}
