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

package de.behrfried.wikianalyzer.wawebapp.client.presenter.mock;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

public class MockArticlePresenter implements ArticleView.Presenter {

	public String getArticleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setArticleName(String string) {
		// TODO Auto-generated method stub
		
	}

	public Event<EventArgs> articleNameChanged() {
		// TODO Auto-generated method stub
		return null;
	}

	public Command getSendCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getArticleHtml() {
		// TODO Auto-generated method stub
		return null;
	}

	public Event<EventArgs> articleHtmlChanged() {
		// TODO Auto-generated method stub
		return null;
	}


}