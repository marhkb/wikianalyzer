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

package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView;

/**
 * Default implementation for {@link ArticleView.Presenter}.
 * 
 * @author marcus
 * 
 */
public class DefaultArticlePresenter implements ArticleView.Presenter {

	@Override
    public LinkedHashMap<String, String> getArticleSuggestions() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> articleSuggestionsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getArticleTitle() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setArticleTitle(String title) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public Event<EventArgs> articleTitleChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getSendCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }


	@Override
    public Command getAnalyzeArticleWordsCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getAnalyzeCategoriesCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getAnalyzeAuthorsCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getAnalyzeEditsCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getAnalyzeTranslationsCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Date getFromTime() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setFromTime(Date fromTime) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public Event<EventArgs> fromTimeChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Date getToTime() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setToTime(Date toTime) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public Event<EventArgs> toTimeChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getArticleLink() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> articleLinkChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Date getArticleCreationDate() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> articleCreationDateChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getInitialAuthorLink() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> initialAuthorLinkChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public int getNumberOfTranslations() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public Event<EventArgs> numberOfTranslationChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public int getNumberOfRevisions() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public Event<EventArgs> numberOfRevisionsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public int getNumberOfAuthors() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public Event<EventArgs> numberOfAuthorsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public List<String> getArticleCategories() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> articleCategoriesChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public int getNumberOfArticleWords() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public Event<EventArgs> numberOfArticleWordsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public boolean getSearchStatus() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public Event<EventArgs> userLinkChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> wikiLinkChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }
}