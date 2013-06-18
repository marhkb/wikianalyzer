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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class MockArticlePresenter implements ArticleView.Presenter {

	private final MainServiceAsync mainService;

	private final Object initContext = new Object();

	@Inject
	public MockArticlePresenter(final MainServiceAsync mainService) throws IllegalArgumentException {
		if(mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		this.mainService = mainService;
	}

	private String articleName = "";

	public String getArticleTitle() {
		return this.articleName;
	}

	private boolean searched = false;

	public boolean getSearchStatus() {
		return this.searched;
	}

	public void setSearchStatus(boolean searched) {
		this.searched = searched;
		CommandManager.get().invalidateRequerySuggested();
	}

	public void setArticleTitle(final String string) {
		if(!string.equals(this.articleName)) {
			this.articleName = string;
			this.articleTitleChanged().fire(this.initContext, this, EventArgs.EMPTY);
			this.jGetArticleTitles(this.articleName, 10);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	private final Event<EventArgs> articleChanged = new Event<EventArgs>(this.initContext);

	public Event<EventArgs> articleTitleChanged() {
		return this.articleChanged;
	}

	private Command sendCommand, analyzeTranslationsCommand, analyzeEditsCommand, analyzeAuthorsCommand, analyzeCategoriesCommand,
	        analyzeArticleLengthCommand;

	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {

				public void execute(final Object param) {

					MockArticlePresenter.this.mainService.sendArticleName(MockArticlePresenter.this.getArticleTitle(), new AsyncCallback<Integer>() {

						int number = 0;

						public void onSuccess(final Integer result) {
							this.number = new Random().nextInt();
							setArticleLink("www." + number + ".org");
							setFromTime(new Date());
							setInitialAuthorLink("www.user" + number + ".org");
							setNumberOfAuthors(number);
							setNumberOfRevisions(number);
							setSearchStatus(true);
						}

						public void onFailure(final Throwable caught) {}
					});
				}

				public boolean canExecute(final Object param) {
					return MockArticlePresenter.this.getArticleTitle().length() > 0;
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockArticlePresenter.this.getSendCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.sendCommand;
	}

	@Override
	public Command getAnalyzeArticleWordsCommand() {
		if(this.analyzeArticleLengthCommand == null) {
			this.analyzeArticleLengthCommand = new UICommand() {

				public void execute(final Object param) {
					// TODO
				}

				public boolean canExecute(final Object param) {
					return MockArticlePresenter.this.getSearchStatus();
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockArticlePresenter.this.getAnalyzeArticleWordsCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.analyzeArticleLengthCommand;
	}

	@Override
	public Command getAnalyzeCategoriesCommand() {
		if(this.analyzeCategoriesCommand == null) {
			this.analyzeCategoriesCommand = new UICommand() {

				public void execute(final Object param) {
					// TODO
				}

				public boolean canExecute(final Object param) {
					return MockArticlePresenter.this.getSearchStatus();
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockArticlePresenter.this.getAnalyzeCategoriesCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.analyzeCategoriesCommand;
	}

	@Override
	public Command getAnalyzeAuthorsCommand() {
		if(this.analyzeAuthorsCommand == null) {
			this.analyzeAuthorsCommand = new UICommand() {

				public void execute(final Object param) {
					// TODO
				}

				public boolean canExecute(final Object param) {
					return MockArticlePresenter.this.getSearchStatus();
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockArticlePresenter.this.getAnalyzeAuthorsCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.analyzeAuthorsCommand;
	}

	@Override
	public Command getAnalyzeEditsCommand() {
		if(this.analyzeEditsCommand == null) {
			this.analyzeEditsCommand = new UICommand() {

				public void execute(final Object param) {
					// TODO
				}

				public boolean canExecute(final Object param) {
					return MockArticlePresenter.this.getSearchStatus();
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockArticlePresenter.this.getAnalyzeEditsCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.analyzeEditsCommand;
	}

	private String articleLink = "";

	private void setArticleLink(final String articleLink) {
		if(!this.articleLink.equals(articleLink)) {
			this.articleLink = articleLink;
			this.articleLinkChanged().fire(this.initContext, this, EventArgs.EMPTY);

		}
	}

	public String getArticleLink() {
		return this.articleLink;
	}

	private final Event<EventArgs> articleLinkChanged = new Event<EventArgs>(this.initContext);

	public Event<EventArgs> articleLinkChanged() {
		return this.articleLinkChanged;
	}

	private final LinkedHashMap<String, String> suggestions = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getArticleSuggestions() {
		return this.suggestions;
	}

	private final Event<EventArgs> suggestionsChanged = new Event<EventArgs>(this.initContext);

	public Event<EventArgs> articleSuggestionsChanged() {
		return this.suggestionsChanged;
	}

	private void fireSuggestionsChanged() {
		this.suggestionsChanged.fire(this.initContext, this, EventArgs.EMPTY);
	}

	private final void clearSuggestions() {
		this.suggestions.clear();
	}

	private final void addToSuggestions(final String name) {
		this.suggestions.put(name, name);
	}

	public final native void jGetArticleTitles(String word, int maxResults) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::clearSuggestions()();
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::fireSuggestionsChanged()();
		var inst = this;
		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&generator=allpages&callback=?",
						{
							gaplimit : maxResults,
							gapfrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::clearSuggestions()();
							for ( var d in data["query"]["pages"]) {
								var title = data["query"]["pages"][d].title;
								inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::addToSuggestions(Ljava/lang/String;)(title);

							}
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::fireSuggestionsChanged()();
						});
	}-*/;

	private Date fromTime = null;

	private final Event<EventArgs> wikiLinkChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> wikiLinkChanged() {
		return this.wikiLinkChanged;
	}

	@Override
	public Date getFromTime() {
		return this.fromTime;
	}

	@Override
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	private final Event<EventArgs> fromTimeChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> fromTimeChanged() {
		return this.fromTimeChanged;
	}

	private Date toTime;

	@Override
	public Date getToTime() {
		return this.toTime;
	}

	@Override
	public void setToTime(Date toTime) {
		this.toTime = toTime;

	}

	private final Event<EventArgs> toTimeChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> toTimeChanged() {
		return this.toTimeChanged;
	}

	private Date articleCreationDate = null;

	@Override
	public Date getArticleCreationDate() {
		return this.articleCreationDate;
	}

	public void setArticleCreationDate(Date articleCreationDate) {
		this.articleCreationDate = articleCreationDate;
		this.articleCreationDateChanged().fire(this.initContext, this, EventArgs.EMPTY);
	}

	private Event<EventArgs> articleCreationDateChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> articleCreationDateChanged() {
		return this.articleCreationDateChanged;
	}

	private String initialAuthorLink = "";

	@Override
	public String getInitialAuthorLink() {
		return this.initialAuthorLink;
	}

	private void setInitialAuthorLink(String initialAuthorLink) {
		this.initialAuthorLink = initialAuthorLink;
		this.initialAuthorLinkChanged().fire(initContext, this, EventArgs.EMPTY);
	}

	private final Event<EventArgs> initialAuthorLinkChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> initialAuthorLinkChanged() {
		return this.initialAuthorLinkChanged;
	}

	private int numberOfRevisions;

	@Override
	public int getNumberOfRevisions() {
		return this.numberOfRevisions;
	}

	private void setNumberOfRevisions(int numberofRevisions) {
		this.numberOfRevisions = numberofRevisions;
		this.numberOfRevisionsChanged().fire(initContext, this, EventArgs.EMPTY);
	}

	private final Event<EventArgs> numberOfRevisionsChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> numberOfRevisionsChanged() {
		return this.numberOfRevisionsChanged;
	}

	private int numberOfAuthors;

	@Override
	public int getNumberOfAuthors() {
		return this.numberOfAuthors;
	}

	private void setNumberOfAuthors(int numberOfAuthors) {
		this.numberOfAuthors = numberOfAuthors;
		this.numberOfAuthorsChanged().fire(initContext, this, EventArgs.EMPTY);
	}

	private final Event<EventArgs> numberOfAuthorsChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> numberOfAuthorsChanged() {
		return this.numberOfAuthorsChanged;
	}

	private List<String> articleCategories = null;

	@Override
	public List<String> getArticleCategories() {
		return this.articleCategories;
	}

	private final Event<EventArgs> articleCategoriesChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> articleCategoriesChanged() {
		return this.articleCategoriesChanged;
	}

	private int numberofArticleWords;

	@Override
	public int getNumberOfArticleWords() {
		return this.numberofArticleWords;
	}

	private final Event<EventArgs> numberOfArticleWordsChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> numberOfArticleWordsChanged() {
		return this.numberOfArticleWordsChanged;
	}

	@Override
	public boolean getHasInfoBox() {
		// TODO Auto-generated method stub
		return false;
	}

	private final Event<EventArgs> infoBoxChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> infoBoxChanged() {
		return this.infoBoxChanged;
	}

	@Override
	public int getNumberOfPictures() {
		// TODO Auto-generated method stub
		return 0;
	}

	private final Event<EventArgs> numberOfPicturesChanged = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> numberOfPicturesChanged() {
		return this.numberOfPicturesChanged;
	}

	@Override
	public boolean articleNeedsEdit() {
		// TODO Auto-generated method stub
		return false;
	}

	private final Event<EventArgs> needEditsChanges = new Event<EventArgs>(initContext);

	@Override
	public Event<EventArgs> articleNeedsEditsChanged() {
		return this.needEditsChanges;
	}
}
