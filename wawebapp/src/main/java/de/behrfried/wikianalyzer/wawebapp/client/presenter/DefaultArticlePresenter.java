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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.engine.UICommand;
import de.behrfried.wikianalyzer.wawebapp.client.event.FieldChangedEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

/**
 * Default implementation for {@link ArticleView.Presenter}.
 * 
 * @author marcus
 * 
 */
public class DefaultArticlePresenter implements ArticleView.Presenter {

	private final MainServiceAsync mainService;

	private final Object initContext = new Object();

	@Inject
	public DefaultArticlePresenter(final MainServiceAsync mainService) {
		this.mainService = mainService;

	}

	private String articleName = "";
	

	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String string) {
		if(!string.equals(this.articleName)) {
			this.articleName = string;
			//this.articleName = "$" + this.articleName;
			this.articleNameChanged()
			    .fire(this.initContext, this, EventArgs.EMPTY);
			this.sendCommand.raiseCanExecuteChanged();
		}
	}

	private final Event<EventArgs> articleChanged = new Event<EventArgs>(initContext);
	public Event<EventArgs> articleNameChanged() {
		return articleChanged;
	}

	private final Command sendCommand = new UICommand() {
		
		public void execute(Object param) {
			setArticleName(getArticleName().toUpperCase());
		}
		
		public boolean canExecute(Object param) {
			return getArticleName().length() > 0;
		}
		
		@Override
		protected EventArgs getEventArgs() {
			return EventArgs.EMPTY;
		}
	};
	
	public Command getSendCommand() {
		return this.sendCommand;
	}

	public String getArticleHtml() {
		// TODO Auto-generated method stub
		return null;
	}

	public Event<EventArgs> articleHtmlChanged() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListGridRecord[] getArticleInfos() {
		// TODO Auto-generated method stub
		return null;
	}
}