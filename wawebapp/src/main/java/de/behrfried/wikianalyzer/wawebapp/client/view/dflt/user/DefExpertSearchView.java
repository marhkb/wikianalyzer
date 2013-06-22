package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.usercomparison.DefUserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;


public class DefExpertSearchView extends ExpertSearchView {
	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments
	 * are injected by Gin
	 * 
	 * @param parentView
	 * @throws IllegalArgumentException
	 */
	@Inject
	public DefExpertSearchView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
	}
	
	public void init() {
		
	}
}
