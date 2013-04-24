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

import com.google.inject.Inject;
import com.smartgwt.client.widgets.tab.Tab;

import de.behrfried.wikianalyzer.wawebapp.client.Messages;

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
	private final TabContainerView parentView;
	private final Messages messages;

	/**
	 * Creates an instance of {@link DefaultUserView}. All arguments are
	 * injected by Gin.
	 * 
	 * @param parentView
	 */
	@Inject
	public DefaultUserView(TabContainerView parentView, Messages messages) {
		this.parentView = parentView;
		this.messages = messages;
	}

	private Tab userTab;

	/**
	 * @see View
	 */
	public void init() {
		this.userTab = new Tab("User");
		this.parentView.getMainTabContainer().addTab(this.userTab);
	}

	/**
	 * @see
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
