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

package de.behrfried.wikianalyzer.util.command;

import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;

/**
 * Interface for Commands. Its implementation should override 'execute' and
 * 'canExecute' and put their own logic in
 * 
 * @author marcus
 * 
 */
public interface Command {

	/**
	 * Method to be executed by the Command.
	 * 
	 * @param param
	 *            additional {@link Object} that can be used by the Command
	 */
	void execute(Object param);

	/**
	 * Returns a boolean to determine whether the Command can be executed.
	 * 
	 * @param param
	 *            additional {@link Object} that can be used by the Command
	 * @return true if the Command can be executed otherwise false
	 */
	boolean canExecute(Object param);
	
	/**
	 * Gets fired when canExecute could have changed.
	 * @return an {@link Event}
	 */
	public Event<EventArgs> canExecuteChanged();
}
