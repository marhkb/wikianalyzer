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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.command.CommandManager;
import de.behrfried.wikianalyzer.util.command.UICommand;
import de.behrfried.wikianalyzer.util.data.Tuple;
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.event.Handler;
import de.behrfried.wikianalyzer.util.list.ObservableLinkedList;
import de.behrfried.wikianalyzer.util.list.ObservableList;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

public class MockArticlePresenter implements ArticleView.Presenter {

	private final MainServiceAsync mainService;

	private final Object initContext = new Object();

	@Inject
	public MockArticlePresenter(final MainServiceAsync mainService) throws IllegalArgumentException {
		if(mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		this.mainService = mainService;

		this.articleInfos.add(Tuple.create("Ast", "Loch"));
		this.articleInfos.add(Tuple.create("Rosa", "Schlüpfer"));
	}

	private String articleName = "";

	public String getArticleTitle() {
		return this.articleName;
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

	private Command sendCommand;

	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {

				public void execute(final Object param) {

					MockArticlePresenter.this.mainService.sendArticleName(MockArticlePresenter.this.getArticleTitle(), new AsyncCallback<Integer>() {

						public void onSuccess(final Integer result) {
							Window.alert(result + "");
						}

						public void onFailure(final Throwable caught) {}
					});

					final Random r = new Random();
					switch(r.nextInt(5)) {
						case 0:
							MockArticlePresenter.this.setArticleLink("http://www.google.de");
							break;
						case 1:
							MockArticlePresenter.this.setArticleLink("http://www.golem.de");
							break;
						case 2:
							MockArticlePresenter.this.setArticleLink("http://www.mit.de");
							break;
						case 3:
							MockArticlePresenter.this.setArticleLink("http://www.yahoo.de");
							break;
						case 4:
							MockArticlePresenter.this.setArticleLink("http://www.pampers.de");
							break;
						default:
							MockArticlePresenter.this.setArticleLink("http://youporn.com");
							break;
					}
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

	private final ObservableList<Tuple2<String, String>> articleInfos = new ObservableLinkedList<Tuple2<String, String>>(
	        new LinkedList<Tuple2<String, String>>());

	public ObservableList<Tuple2<String, String>> getArticleInfos() {
		return this.articleInfos;
	}

	private final LinkedHashMap<String, String> suggestions = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getSuggestions() {
		return this.suggestions;
	}

	private final Event<EventArgs> suggestionsChanged = new Event<EventArgs>(this.initContext);

	public Event<EventArgs> suggestionsChanged() {
		return this.suggestionsChanged;
	}

	void fireSuggestionsChanged() {
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
	                                                                        {gaplimit: maxResults, gapfrom: word}, 
	                                                                        function(data) {
	                                                                        inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::clearSuggestions()();
	                                                                        for ( var d in data["query"]["pages"]) {
	                                                                        var title = data["query"]["pages"][d].title;
	                                                                        inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::addToSuggestions(Ljava/lang/String;)(title);

	                                                                        }
	                                                                        inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::fireSuggestionsChanged()();
	                                                                        });
	                                                                        }-*/;

    public String getSearchedArticleURL() {
	    return "www.google.de";
    }
    public String getSearchedArticleUser() {
	    return "MAC ";
    }
    public Event<EventArgs> searchedArticleURLChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }
}
