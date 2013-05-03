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

import java.util.LinkedHashMap;
import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.list.ObservableList;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

/**
 * Default implementation for {@link ArticleView.Presenter}.
 * 
 * @author marcus
 * 
 */
public class DefaultArticlePresenter implements ArticleView.Presenter {

	public String getArticleTitle() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public void setArticleTitle(String string) {
	    // TODO Auto-generated method stub
	    
    }

	public Event<EventArgs> articleTitleChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public Command getSendCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public String getArticleLink() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public Event<EventArgs> articleLinkChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public ObservableList<Tuple2<String, String>> getArticleInfos() {
	    // TODO Auto-generated method stub
	    return null;
    }


	public Event<EventArgs> suggestionsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	public LinkedHashMap<String, String> getSuggestions() {
	    // TODO Auto-generated method stub
	    return null;
    }

}