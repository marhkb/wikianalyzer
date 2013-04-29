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
import de.behrfried.wikianalyzer.util.command.Command;

/**
 * Singleton class for holding {@link Command}s and invoking their {@code raiseExecuteChanged()}.
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
	 * Returns the Singleton CommandManager instance.
	 * @return
	 */
	public static CommandManager get() {
		return INSTANCE;
	}

	/**
	 * stores {@link Command}s
	 */
	private final Set<Command> commands = new HashSet<Command>();

	/**
	 * Private constructor to prevent instantiation.
	 */
	private CommandManager() {}

	/**
	 * Adds the passed {@link Command} to the CommandManager.
	 * @param command the Command to be added to the CommandManager
	 * @return <code>true</code> if passed Command didn't exist the CommandManager before
	 * @throws IllegalArgumentException if command == <code>null</code>
	 */
	public boolean addCommand(final Command command) throws IllegalArgumentException {
		if(command == null) {
			throw new IllegalArgumentException("command == null");
		}
		return this.commands.add(command);
	}

	/**
	 * Removes the passed {@link Command} from the CommandManager.
	 * @param command the Command to be removed from the CommandManager
	 * @return <code>true</code> if the CommandManager has contained the passed Command and could remove it, otherwise <code>false</code>
	 * @throws IllegalArgumentException if command == <code>null</code>
	 */
	public boolean removeCommand(final Command command) throws IllegalArgumentException {
		if(command == null) {
			throw new IllegalArgumentException("command == null");
		}
		return this.commands.remove(command);
	}

	/**
	 * Invokes the {@code raiseCanExecuteChanged()} of each {@link Command}.
	 */
	public void invalidateRequerySuggested() {
		for(final Command command : this.commands) {
			command.raiseCanExecuteChanged();
		}
	}
}
