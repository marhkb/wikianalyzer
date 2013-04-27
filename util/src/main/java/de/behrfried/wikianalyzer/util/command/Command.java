package de.behrfried.wikianalyzer.util.command;

public interface Command {
	void execute(Object param);
	boolean canExecute(Object Param);
}
