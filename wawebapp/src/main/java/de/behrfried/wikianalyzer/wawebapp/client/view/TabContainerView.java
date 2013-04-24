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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Default implementation of {@link StartPageView}
 * @author marcus
 *
 */
public class TabContainerView implements StartPageView  {
	private TabLayoutPanel mainTabContainer;
	
    public void init() {
		this.mainTabContainer = new TabLayoutPanel(20,Style.Unit.PX);
    }

    public TabLayoutPanel getMainTabContainer() {
		return mainTabContainer;
	}

	public void setMainTabContainer(TabLayoutPanel mainTabContainer) {
		this.mainTabContainer = mainTabContainer;
	}

	public void dispose() {
	// TODO Auto-generated method stub
    }
}
