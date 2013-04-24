/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Default implementation of {@link TabContainerView}
 * 
 * @author marcus
 * 
 */
public class DefaultTabContainerView implements TabContainerView {
    private TabSet mainTabContainer;
    
    @Inject
    public DefaultTabContainerView() {
    }
    
    public void init() {
	this.mainTabContainer = new TabSet();
	this.mainTabContainer.setWidth100();
	this.mainTabContainer.setHeight100();
	RootPanel.get().add(this.mainTabContainer);
	
    }
    
    public TabSet getMainTabContainer() {
	return mainTabContainer;
    }
    
    public void dispose() {
	// TODO Auto-generated method stub
    }
}
