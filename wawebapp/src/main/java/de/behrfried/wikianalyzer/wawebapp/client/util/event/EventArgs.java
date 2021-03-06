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

package de.behrfried.wikianalyzer.wawebapp.client.util.event;

/**
 * Base class for all EventArgs. An EventArg is passed to the callback method
 * when an {@link de.behrfried.wikianalyzer.wawebapp.client.util.event.Event} fires.
 * 
 * @author marcus
 * 
 */
public class EventArgs {

	/**
	 * An empty EventArgs
	 */
	public static final EventArgs EMPTY = new EventArgs();
}
