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

import java.util.LinkedHashMap;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;

public abstract class UserComparisonView extends View {

	/**
	 * Base interface for user comparison presenters.
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {

		UserComparisonInfo getUserComparisonInfo();

		Event<EventArgs> userComparisonInfoChanged();


		/**
		 * contains the search suggestions for articles
		 * @return
		 */
		LinkedHashMap<String, String> getUser1Suggestions();
		
		/**
		 * contains the search suggestions for articles
		 * @return
		 */
		LinkedHashMap<String, String> getUser2Suggestions();

		/**
		 * is to be fired when suggestion has changed
		 * @return
		 */
		Event<EventArgs> user1SuggestionsChanged();

		/**
		 * is to be fired when suggestion has changed
		 * @return
		 */
		Event<EventArgs> user2SuggestionsChanged();

		/**
		 * returns the current article to be searched
		 * @return
		 */
		String getUserName1();
		
		/**
		 * returns the current article to be searched
		 * @return
		 */
		String getUserName2();

		/**
		 * sets the current article to be searched
		 * @param title
		 */
		void setUserName1(String userName);
		
		/**
		 * sets the current article to be searched
		 * @param title
		 */
		void setUserName2(String userName);

		/**
		 * gets fired when article title has changed
		 * @return
		 */
		Event<EventArgs> userName1Changed();
		/**
		 * gets fired when article title has changed
		 * @return
		 */
		Event<EventArgs> userName2Changed();

		/**
		 * returns the search command
		 */
		Command getSendCommand();

		/**
		 *
		 */
		boolean getSearchStatus();

		Event<EventArgs> searchStatusChanged();
	}
}
