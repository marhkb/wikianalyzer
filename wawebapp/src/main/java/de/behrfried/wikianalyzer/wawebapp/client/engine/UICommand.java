package de.behrfried.wikianalyzer.wawebapp.client.engine;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;

public abstract class UICommand implements Command {

	protected final Object initContext = new Object();
	private final Event<EventArgs> canExecutedChanged = new Event<EventArgs>(this.initContext);
	
	public Event<EventArgs> canExecuteChanged() {
		return this.canExecutedChanged;
	}
	
	protected void raiseCanExecuteChanged(final EventArgs e) {
		this.canExecuteChanged().fire(this.initContext, this, e);
	}
}
