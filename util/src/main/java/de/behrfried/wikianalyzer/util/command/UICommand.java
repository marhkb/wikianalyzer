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
	 * the {@link Event} returned in {@code canExecuteChanged}
	 */
	private final Event<EventArgs> canExecuteChanged = new Event<EventArgs>(this.initContext);
	
	/**
	 * {@inheritDoc}
	 */
	public Event<EventArgs> canExecuteChanged() {
		return this.canExecuteChanged;
	}

	/**
	 * {@inheritDoc}
	 */
	public void raiseCanExecuteChanged() {
		this.canExecuteChanged().fire(this.initContext, this, this.getEventArgs());
	}

	/**
	 * Returns an {@link EventArgs} used when {@code canExecudeChanged()} is raised.
	 * @return an {@link EventArgs} used when {@code canExecudeChanged()} is raised
	 */
	protected abstract EventArgs getEventArgs();
}
