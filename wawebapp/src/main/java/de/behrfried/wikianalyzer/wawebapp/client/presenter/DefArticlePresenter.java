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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.CommandManager;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.UICommand;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;

import java.util.*;

public class DefArticlePresenter implements ArticleView.Presenter {
	private final static Object INIT_CONTEXT = new Object();
	private final Event<EventArgs> articleInfoChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> articleChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> suggestionsChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> searchStatusChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final LinkedHashMap<String, String> suggestions = new LinkedHashMap<String, String>();
	private final MainServiceAsync mainService;
	private ArticleInfo articleInfo;
	private String articleTitle = "";
	private Command sendCommand;
	private boolean searched = true;


	@Inject
	public DefArticlePresenter(final MainServiceAsync mainService) throws IllegalArgumentException {
		if(mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		this.mainService = mainService;
	}

	@Override
	public ArticleInfo getArticleInfo() {
		return this.articleInfo;
	}

	public void setArticleInfo(ArticleInfo articleInfo) {
		this.articleInfo = articleInfo;
		this.articleInfoChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	@Override
	public Event<EventArgs> articleInfoChanged() {
		return this.articleInfoChanged;
	}

	public boolean getSearchStatus() {
		return this.searched;
	}

	public void setSearchStatus(boolean searched) {
		if(this.searched != searched) {
			this.searched = searched;
			this.searchStatusChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	@Override
	public Event<EventArgs> searchStatusChanged() {
		return this.searchStatusChanged;
	}

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(final String articleTitle) {
		if(!articleTitle.equals(this.articleTitle)) {
			this.articleTitle = articleTitle;
			this.articleTitleChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
			this.jGetArticleTitles(this.articleTitle, 10);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	public Event<EventArgs> articleTitleChanged() {
		return this.articleChanged;
	}

	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {

				public void execute(final Object param) {
					setSearchStatus(false);
					DefArticlePresenter.this.mainService.sendArticleName(
							DefArticlePresenter.this.getArticleTitle(),
							new AsyncCallback<ArticleInfo>() {

								public void onSuccess(final ArticleInfo result) {
									setSearchStatus(true);
									setArticleInfo(result);
								}

								public void onFailure(final Throwable caught) {
									Window.alert(caught.getMessage());
								}
							}
					);
				}

				public boolean canExecute(final Object param) {
					return DefArticlePresenter.this.getArticleTitle().length() > 0;
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(
					new Handler<EventArgs>() {
						public void invoke(final Object sender, final EventArgs e) {
							DefArticlePresenter.this.getSendCommand().raiseCanExecuteChanged();
						}
					}
			);
		}
		return this.sendCommand;
	}

	@Override
	public LinkedHashMap<String, String> getArticleSuggestions() {
		return this.suggestions;
	}
	@Override
	public Event<EventArgs> articleSuggestionsChanged() {
		return this.suggestionsChanged;
	}

	private void fireSuggestionsChanged() {
		this.suggestionsChanged.fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	private final void clearSuggestions() {
		this.suggestions.clear();
	}

	private final void addToSuggestions(final String name) {
		this.suggestions.put(name, name);
	}

	public final native void jGetArticleTitles(String word, int maxResults) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefArticlePresenter::clearSuggestions()();
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefArticlePresenter::fireSuggestionsChanged()();
		var inst = this;
		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&generator=allpages&callback=?",
						{
							gaplimit : maxResults,
							gapfrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefArticlePresenter::clearSuggestions()();
							for ( var d in data["query"]["pages"]) {
								var title = data["query"]["pages"][d].title;
								inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefArticlePresenter::addToSuggestions(Ljava/lang/String;)(title);

							}
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefArticlePresenter::fireSuggestionsChanged()();
						});
	}-*/;
}
