package de.behrfried.wikianalyzer.wawebapp.client.engine;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.data.Tuple;
import de.behrfried.wikianalyzer.util.data.Tuple3;

public class CommandManager {
	
	public interface GenericGetter<E> {
		public E get();
	}
	
	private final static CommandManager INSTANCE = new CommandManager();
	
	public static CommandManager getInstance() {
		return INSTANCE;
	}
	
	private CommandManager() {}
	
	private final Map<Canvas, Tuple3<Command, HandlerRegistration, GenericGetter<Object>>> commands = 
			new HashMap<Canvas, Tuple3<Command, HandlerRegistration, GenericGetter<Object>>>();
	

	public void setCommand(final Canvas canvas, final Command command, final GenericGetter<Object> paramGetter) throws IllegalStateException, IllegalArgumentException {
		if(this.commands.containsKey(canvas)) {
			this.removeCommand(canvas);
		}
		final HandlerRegistration handlerRegistration = canvas.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				command.execute(paramGetter.get());
			}
		});
		this.commands.put(canvas, Tuple.create(command, handlerRegistration, paramGetter));
		canvas.setDisabled(!command.canExecute(paramGetter.get()));
	}
	
	public void removeCommand(final Canvas canvas) {
		this.commands.remove(canvas).getItem2().removeHandler();
	}
	
	public void requerySuggested() {
		for(final Map.Entry<Canvas, Tuple3<Command, HandlerRegistration, GenericGetter<Object>>> entry : this.commands.entrySet()) {
			entry.getKey().setDisabled(!entry.getValue().getItem1().canExecute(entry.getValue().getItem3().get()));
		}
	}
}
