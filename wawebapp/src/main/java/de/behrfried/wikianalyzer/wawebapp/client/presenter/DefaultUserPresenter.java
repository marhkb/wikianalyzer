package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import de.behrfried.wikianalyzer.wawebapp.client.view.UserView;

/**
 * Default implementation of {@link UserPresenter}.
 * @author marcus
 *
 */
public class DefaultUserPresenter implements UserPresenter {

    /**
     * the {@link DefaultUserPresenter}'s {@link UserView}
     */
    private final UserView view;
    
    /**
     * Creates a {@link DefaultUserPresenter}. All arguments are injected by Gin.
     * @param view the {@link DefaultUserPresenter}'s {@link UserView}
     * @throws IllegalArgumentException if view == null
     */
    public DefaultUserPresenter(final UserView view) throws IllegalArgumentException {
	if(view == null) {
	    throw new IllegalArgumentException("view == null");
	}
	this.view = view;
    }
    
    /**
     * @see Presenter
     */
    public void init() {
	// TODO Auto-generated method stub
	
    }

    /**
     * @see Presenter
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }
    
}
