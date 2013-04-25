package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * Default implementation for {@link UserComparisonView}.
 * @author marcus
 *
 */
public class DefaultUserComparisonView implements UserComparisonView {
    
    /**
     * parent view of this {@link DefaultUserComparisonView}
     */
    private final TabContainerView parentView;
    
    /**
     * Creates an instance of {@link DefaultUserComparisonView}. All arguments are injected by Gin
     * @param parentView
     * @throws IllegalArgumentException
     */
    @Inject
    public DefaultUserComparisonView(final TabContainerView parentView)
	    throws IllegalArgumentException {
	if(parentView == null) {
	    throw new IllegalArgumentException("parentView == null");
	}
	this.parentView = parentView;
    }
    
    private Tab userComprisonTab;
    
    /**
     * @see View
     */
    public void init() {
	this.userComprisonTab = new Tab("User Comparison");
	this.parentView.getMainTabContainer().addTab(this.userComprisonTab);
    }
    
    /**
     * @see View
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }
    
}
