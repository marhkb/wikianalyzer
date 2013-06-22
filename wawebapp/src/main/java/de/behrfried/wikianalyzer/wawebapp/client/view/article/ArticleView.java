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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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


		List<ArticleInfo.AuthorAndCommits> getAuthorAndCommits();

		/**
		 *
		 */
		Event<EventArgs> authorsAndCommitsChanged();


		List<ArticleInfo.Revision> getRevisions();

		/**
		 *
		 */
		Event<EventArgs> revisionsChanged();


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
		 * returns the article words analyzation command
		 * @return
		 */
		Command getAnalyzeArticleWordsCommand();

		/**
		 * returns the category analyzation command 
		 * @return
		 */
		Command getAnalyzeCategoriesCommand();

		/**
		 * returns the authors analyzation command 
		 * @return
		 */
		Command getAnalyzeAuthorsCommand();

		/**
		 * returns the article edits analyzation command 
		 * @return
		 */
		Command getAnalyzeEditsCommand();

		/**
		 * returns the time where the analyzation starts
		 * @return
		 */
		Date getFromTime();
		
		/**
		 * sets the time where the analyzation starts
		 * @param fromTime
		 */
		void setFromTime(Date fromTime);
		
		/**
		 * gets fired when fromTime has changed
		 * @return
		 */
		Event<EventArgs> fromTimeChanged();
		
		/**
		 * returns the time where the analyzation ends
		 * @return
		 */
		Date getToTime();

		/**
		 * sets the time where the analyzation ends
		 * @param toTime
		 */
		void setToTime(Date toTime);

		/**
		 * gets fired when toTime has changed
		 * @return
		 */
		Event<EventArgs> toTimeChanged();
		
		/**
		 * returns the link to the article
		 * @return
		 */
		String getArticleLink();

		/**
		 * gets fired when article link changed
		 * @return
		 */
		Event<EventArgs> articleLinkChanged();
		
		/**
		 * returns the article's creation date
		 */
		Date getArticleCreationDate();
		
		/**
		 * gets fired when the article's creation date changes
		 * @return
		 */
		Event<EventArgs> articleCreationDateChanged();
		
		/**
		 * returns the link to the initial author of the searched article
		 * @return
		 */
		String getInitialAuthorLink();
		
		/**
		 * get fired when the initial author changes
		 * @return
		 */
		Event<EventArgs> initialAuthorLinkChanged();
		
		/**
		 * returns the number of revisions for the searched article
		 */
		int getNumberOfRevisions();
		
		/**
		 * gets fired when the number of revisions has changed
		 */
		Event<EventArgs> numberOfRevisionsChanged();
		
		/**
		 * returns the number of authors of the searched article
		 */
		int getNumberOfAuthors();
		
		/**
		 * gets fired when the number of authors has changed
		 */
		Event<EventArgs> numberOfAuthorsChanged();
		
		/**
		 * returns the searched articles category
		 */
		List<String> getArticleCategories();
		
		/**
		 * gets fired when the article's categories have changed
		 */
		Event<EventArgs> articleCategoriesChanged();
		
		/**
		 * returns the number of words in the searched article
		 */
		int getNumberOfArticleWords();
		
		/**
		 * gets fired when the number of the article's words has changed
		 */
		Event<EventArgs> numberOfArticleWordsChanged();
		
		/**
		 * 
		 */
		boolean getSearchStatus();
		
		Event<EventArgs> wikiLinkChanged();

		boolean getHasInfoBox();

		Event<EventArgs> infoBoxChanged();

		boolean articleNeedsEdit();

		Event<EventArgs> numberOfPicturesChanged();

		int getNumberOfPictures();

		Event<EventArgs> articleNeedsEditsChanged();
		
		boolean isAuthorsShowing();
		
		boolean isCategoriesShowing();
		
		boolean isNumbersOfWordsShowing();
		
		boolean isRevisionsShowing();
		
		boolean isTranslationsShowing();
	}
}
