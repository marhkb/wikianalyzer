package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.inject.Inject;

import de.behrfried.wikianalyzer.wawebapp.client.view.UserComparisonView;

/**
 * Default implementation of {@link UserComparisonPresenter}
 * @author marcus
 *
 */
public class DefaultUserComparisonPresenter implements UserComparisonPresenter {
    
    /**
     * the {@link DefaultUserComparisonPresenter}'s {@link UserComparisonView}
     */
    private final UserComparisonView view;
    
    /**
     * Creates an instance of {@link DefaultUserComparisonPresenter}. All arguments are injected by Gin.
     * @param view the {@link DefaultUserComparisonPresenter}'s {@link UserComparisonView}
     * @throws IllegalArgumentException if view == null
     */
    @Inject
    public DefaultUserComparisonPresenter(final UserComparisonView view) throws IllegalArgumentException {
	if(view == null) {
	    throw new IllegalArgumentException("view == null");
	}
	this.view = view;
    }
    
    /**
     * @see Presenter
     */
    public void init() {
	this.view.init();
	
	// TODO add handlers
	
    }
    
    /**
     * @see Presenter
     */
    public void dispose() {
	this.view.dispose();
	
	// TODO remove handlers
    }
    
}
