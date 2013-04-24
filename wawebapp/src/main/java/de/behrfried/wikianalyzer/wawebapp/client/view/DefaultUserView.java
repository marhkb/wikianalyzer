package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;

/**
 * Default implementation of {@link UserView}.
 * 
 * @author marcus
 * 
 */
public class DefaultUserView implements UserView {
    
    /**
     * {@link DefaultUserView}'s parent element
     */
    private final StartPageView parentView;
    
    /**
     * Creates an instance of {@link DefaultUserView}. All arguments are injected by Gin.
     * 
     * @param parentView
     */
    @Inject
    public DefaultUserView(StartPageView parentView) {
	this.parentView = parentView;
    }
    
    /**
     * @see View
     */
    public void init() {
	// TODO Auto-generated method stub
	
    }
    
    /**
     * @see
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }
    
}
