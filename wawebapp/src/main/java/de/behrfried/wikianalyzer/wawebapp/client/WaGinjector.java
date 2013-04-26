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

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView;

/**
 * Main Injector of the web application. Returns dependencies as declared in
 * {@link WaGinModule}'s 'bind' method.
 * 
 * @author marcus
 * 
 */
@GinModules(WaGinModule.class)
public interface WaGinjector extends Ginjector {

	/**
	 * Returns an implementation of {@link ShellView} as declared in
	 * {@link WaGinModule}'s 'bind' method.
	 * 
	 * @return an implementation of {@link ShellView} as declared in
	 *         {@link WaGinModule}'s 'bind' method
	 */
	ShellView getShellView();
}
