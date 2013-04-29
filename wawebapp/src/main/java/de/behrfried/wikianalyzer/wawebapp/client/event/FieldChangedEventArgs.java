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

package de.behrfried.wikianalyzer.wawebapp.client.event;

import de.behrfried.wikianalyzer.util.event.EventArgs;

/**
 * EventArgs containing the name of a field which value has changed.
 * 
 * @author marcus
 * 
 */
public class FieldChangedEventArgs extends EventArgs {

	/**
	 * the name of the field that has changed
	 */
	private final String fieldName;

	/**
	 * Creates an instance of FieldChangedEventArgs with the passed arguments.
	 * 
	 * @param fieldName
	 *            the name of the field that has changed
	 * @throws IllegalArgumentException
	 *             if fieldName == null
	 */
	public FieldChangedEventArgs(final String fieldName) throws IllegalArgumentException {
		this.fieldName = fieldName;
	}

	/**
	 * Returns the name of the field that has changed.
	 * 
	 * @return the name of the field that has changed
	 */
	public String getFieldName() {
		return this.fieldName;
	}

}
