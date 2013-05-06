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
 * Singleton class for holding {@link Command}s and invoking their
 * {@code raiseExecuteChanged()}.
 * 
 * @author marcus
 * 
 */
public class CommandManager {

	/**
	 * the Singleton CommandManager instance
	 */
	private final static CommandManager INSTANCE = new CommandManager();
	
	/**
	 * 
	 */
	private final Object initContext = new Object();

	/**
	 * Returns the Singleton CommandManager instance.
	 * 
	 * @return
	 */
	public static CommandManager get() {
		return CommandManager.INSTANCE;
	}

	/**
	 * Private constructor to prevent instantiation.
	 */
	private CommandManager() { }
	
	/**
	 * 
	 */
	private final Event<EventArgs> requerySuggested = new Event<EventArgs>(this.initContext);
	
	/**
	 * 
	 * @return
	 */
	public Event<EventArgs> requerySuggested() {
		return this.requerySuggested;
	}

	/**
	 * Invokes the {@code raiseCanExecuteChanged()} of each {@link Command}.
	 */
	public void invalidateRequerySuggested() {
		this.requerySuggested().fire(this.initContext, this, EventArgs.EMPTY);
	}
}
