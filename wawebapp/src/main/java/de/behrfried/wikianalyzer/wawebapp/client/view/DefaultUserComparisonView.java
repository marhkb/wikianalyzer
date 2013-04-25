package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * Default implementation for {@link UserComparisonView}.
 * @author marcus
 *
 */
public class DefaultUserComparisonView extends UserComparisonView {
    
    /**
     * parent view of this {@link DefaultUserComparisonView}
     */
    private final ShellView parentView;
    
    /**
     * Creates an instance of {@link DefaultUserComparisonView}. All arguments are injected by Gin
     * @param parentView
     * @throws IllegalArgumentException
     */
    @Inject
    public DefaultUserComparisonView(final ShellView parentView)
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
    public Canvas init() {
    	this.userComprisonTab = new Tab("User Comparison");
    	return this;
    }
    
    /**
     * @see View
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }

	public String getName() {
		// TODO Auto-generated method stub
		return "User Comparison";
	}
}
