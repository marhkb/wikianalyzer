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

package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Wawebapp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// create the main Ginjector and load ShellView
		final ShellView shellView = ((WaGinjector)GWT.create(WaGinjector.class)).getShellView();
		
		// add the ShellView to RootPanel
		RootPanel.get().add(shellView, 0, 0);
		
		//do post construct action
		shellView.postConstruct();
	}

}
