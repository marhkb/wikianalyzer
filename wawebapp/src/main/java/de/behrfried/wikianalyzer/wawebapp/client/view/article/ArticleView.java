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

package de.behrfried.wikianalyzer.wawebapp.client.view.article;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;

import java.util.LinkedHashMap;

/**
 * Base interface for article view.
 * 
 * @author marcus
 * 
 */
public abstract class ArticleView extends View {

	/**
	 * Presenter interface the ArticleView wants to use
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {


		ArticleInfo getArticleInfo();

		Event<EventArgs> articleInfoChanged();


		/**
		 * contains the search suggestions for articles
		 * @return
		 */
		LinkedHashMap<String, String> getArticleSuggestions();

		/**
		 * is to be fired when suggestion has changed
		 * @return
		 */
		Event<EventArgs> articleSuggestionsChanged();

		/**
		 * returns the current article to be searched
		 * @return
		 */
		String getArticleTitle();

		/**
		 * sets the current article to be searched
		 * @param title
		 */
		void setArticleTitle(String title);

		/**
		 * gets fired when article title has changed
		 * @return
		 */
		Event<EventArgs> articleTitleChanged();

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
