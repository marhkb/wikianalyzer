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

import java.util.HashSet;
import java.util.Set;
import com.smartgwt.client.widgets.Canvas;
import de.behrfried.wikianalyzer.util.command.Command;

/**
 * Default implementation for {@link CommandManager}.
 * 
 * @author marcus
 * 
 */
public class CommandManager {

	private final static CommandManager INSTANCE = new CommandManager();

	public static CommandManager get() {
		return INSTANCE;
	}

	/**
	 * Stores {@link Canvas}es with their registered {@link Command}s
	 */
	private final Set<Command> commands = new HashSet<Command>();

	private CommandManager() {}

	/**
	 * {@inheritDoc}
	 */
	public boolean addCommand(final Command command) throws IllegalArgumentException {
		if(command == null) {
			throw new IllegalArgumentException("command == null");
		}
		return this.commands.add(command);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean removeCommand(final Command command) throws IllegalArgumentException {
		if(command == null) {
			throw new IllegalArgumentException("command == null");
		}
		return this.commands.remove(command);
	}

	/**
	 * {@inheritDoc}
	 */
	public void invalidateRequerySuggested() {
		for(final Command command : this.commands) {
			command.raiseCanExecuteChanged();
		}
	}
}
