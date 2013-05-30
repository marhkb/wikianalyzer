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

package de.behrfried.wikianalyzer.wawebapp.client.view.user;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.list.ObservableList;
import de.behrfried.wikianalyzer.wawebapp.client.event.GenericEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;

/**
 * Base interfaces for the user view.
 * 
 * @author marcus
 * 
 */
public abstract class UserView extends View {

	/**
	 * Presenter interface the UserView wants to use
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {

		public void setNameToServer(final String nameToServer);

		public String getNameToServer();

		public Event<GenericEventArgs<String>> getNameToServerChanged();

		public String getNameToServerErrorMessage();

		public Event<GenericEventArgs<String>> getErrorNameToServerChanged();

		public Command getSendCommand();

		public void setUserName(String valueAsString);

		public Event<EventArgs> usrNameChanged();

		public String getUsrName();

		public ObservableList<Tuple2<String, String>> getUsrInfos();
	}
}
