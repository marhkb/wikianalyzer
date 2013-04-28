package de.behrfried.wikianalyzer.wawebapp.client.engine;

import com.smartgwt.client.widgets.Canvas;
import de.behrfried.wikianalyzer.util.Delegates.Func;
import de.behrfried.wikianalyzer.util.command.Command;

/**
 * Service interface for registering {@link Command}s.
 * 
 * @author marcus
 * 
 */
public interface CommandManager {

	/**
	 * Sets the passed {@link Command} for the passed {@link Canvas}. If there
	 * is already an command associated with this canvas the previous command
	 * will be removed.
	 * 
	 * @param canvas
	 *            the {@link Canvas} on which the {@link Command} will be set
	 * @param command
	 *            the {@link Command} to be set on the the Canvas
	 * @param paramGetter
	 *            an {@link Func} thats returned {@link Object} will be passed
	 *            to {@link Command}'s 'execute' and 'canExecute' method
	 */
	public void setCommand(final Canvas canvas, final Command command, final Func<Object> paramGetter);

	/**
	 * Removes the associated {@link Command} from the passed {@link Canvas}.
	 * 
	 * @param canvas
	 *            the {@link Canvas} which {@link Command} shall be removed.
	 * @throws IllegalArgumentException
	 *             if canvas == null
	 * @throws IllegalStateException
	 *             if no {@link Command} is associated with the passed
	 *             {@link Canvas}
	 */
	public void removeCommand(final Canvas canvas) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Validates every {@link Command} by invoking their 'execute' methods.
	 */
	public void invalidateRequerySuggested();
}
