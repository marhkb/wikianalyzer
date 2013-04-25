/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 
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
