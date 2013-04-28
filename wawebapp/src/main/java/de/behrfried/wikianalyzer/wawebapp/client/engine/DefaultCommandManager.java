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

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import de.behrfried.wikianalyzer.util.Delegates.Func;
import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.data.Tuple;
import de.behrfried.wikianalyzer.util.data.Tuple3;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.event.Handler;

/**
 * Default implementation for {@link CommandManager}.
 * @author marcus
 *
 */
public class DefaultCommandManager implements CommandManager {
	
	/**
	 * Stores {@link Canvas}es with their registered {@link Command}s
	 */
	private final Map<Canvas, Tuple3<Command, HandlerRegistration, Func<Object>>> commands = 
			new HashMap<Canvas, Tuple3<Command, HandlerRegistration, Func<Object>>>();
	
	/**
	 * {@inheritDoc}
	 */
	public void setCommand(final Canvas canvas, final Command command, final Func<Object> paramGetter) throws IllegalStateException, IllegalArgumentException {
		if(this.commands.containsKey(canvas)) {
			this.removeCommand(canvas);
		}
		final HandlerRegistration handlerRegistration = canvas.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				command.execute(paramGetter.invoke());
			}
		});
		command.canExecuteChanged().addHandler(new Handler<EventArgs>() {
			public void invoke(Object sender, EventArgs e) {
				canvas.setDisabled(!command.canExecute(paramGetter.invoke()));
			}
		});
		this.commands.put(canvas, Tuple.create(command, handlerRegistration, paramGetter));
		canvas.setDisabled(!command.canExecute(paramGetter.invoke()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeCommand(final Canvas canvas) throws IllegalArgumentException, IllegalStateException {
		if(canvas == null) {
			throw new IllegalArgumentException("canvas == null");
		}
		if(!this.commands.containsKey(canvas)) {
			throw new IllegalStateException("no command associated");
		}
		this.commands.remove(canvas).getItem2().removeHandler();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void invalidateRequerySuggested() {
		for(final Map.Entry<Canvas, Tuple3<Command, HandlerRegistration, Func<Object>>> entry : this.commands.entrySet()) {
			entry.getKey().setDisabled(!entry.getValue().getItem1().canExecute(entry.getValue().getItem3().invoke()));
		}
	}
}
