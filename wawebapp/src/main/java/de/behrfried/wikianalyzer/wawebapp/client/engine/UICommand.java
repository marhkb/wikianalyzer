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

package de.behrfried.wikianalyzer.wawebapp.client.engine;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;

/**
 * Abstract Command implementing {@link Command}s 'canExecuteChanged' method.
 * 
 * @author marcus
 * 
 */
public abstract class UICommand implements Command {

	/**
	 * the initContext to fire 'canExecutedChanged'
	 */
	protected final Object initContext = new Object();
	
	/**
	 * 
	 */
	public UICommand() {
		CommandManager.get().addCommand(this);
	}

	/**
	 * the event returned in {@code canExecuteChanged}
	 */
	private final Event<EventArgs> canExecutedChanged = new Event<EventArgs>(this.initContext);

	/**
	 * {@inheritDoc}
	 */
	public Event<EventArgs> canExecuteChanged() {
		return this.canExecutedChanged;
	}

	/**
	 * {@inheritDoc}
	 */
	public void raiseCanExecuteChanged() {
		this.canExecuteChanged().fire(this.initContext, this, this.getEventArgs());
	}
	
	/**
	 * 
	 * @return
	 */
	protected abstract EventArgs getEventArgs();
}
