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

import java.util.LinkedList;
import java.util.Random;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.data.Tuple;
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.list.DefaultObservableList;
import de.behrfried.wikianalyzer.util.list.ObservableList;
import de.behrfried.wikianalyzer.wawebapp.client.engine.UICommand;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

public class MockArticlePresenter implements ArticleView.Presenter {

	private final Object initContext = new Object();

	@Inject
	public MockArticlePresenter() {
		this.articleInfos.add(Tuple.create("Ast", "Loch"));
		this.articleInfos.add(Tuple.create("Rosa", "Schlüpfer"));
	}

	private String articleName = "";

	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String string) {
		if(!string.equals(this.articleName)) {
			this.articleName = string;
			this.articleNameChanged().fire(this.initContext, this, EventArgs.EMPTY);
			this.sendCommand.raiseCanExecuteChanged();

			this.suggestions.clear();
			if(this.articleName.startsWith("A")) {
				this.suggestions.add("Arschloch");
				this.suggestions.add("Amerika");
				this.suggestions.add("Atombombe");
			} else {
				this.suggestions.add("Fliegenfänger");
				this.suggestions.add("Menschenfleich");
				this.suggestions.add("Rauch");
				this.suggestions.add("Katasteramt");
				this.suggestions.add("Blubb");
				this.suggestions.add("=<o> : <o>=");
			}
		}
	}

	private final Event<EventArgs> articleChanged = new Event<EventArgs>(initContext);

	public Event<EventArgs> articleNameChanged() {
		return articleChanged;
	}

	private final Command sendCommand = new UICommand() {

		public void execute(Object param) {
			setArticleName(getArticleName().toUpperCase());
			
			final Random r = new Random();
			switch(r.nextInt(5)) {
				case 0:
					setArticleLink("http://www.google.de");
					break;
				case 1:
					setArticleLink("http://www.golem.de");
					break;
				case 2:
					setArticleLink("http://www.mit.de");
					break;
				case 3:
					setArticleLink("http://www.yahoo.de");
					break;
				case 4:
					setArticleLink("http://www.pampers.de");
					break;
				default:
					setArticleLink("http://youporn.com");
					break;
			}
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

	private final ObservableList<Tuple2<String, String>> articleInfos = new DefaultObservableList<Tuple2<String, String>>(
	        new LinkedList<Tuple2<String, String>>());

	public ObservableList<Tuple2<String, String>> getArticleInfos() {
		return this.articleInfos;
	}

	private final ObservableList<String> suggestions = new DefaultObservableList<String>(new LinkedList<String>());

	public ObservableList<String> getSuggestions() {
		return this.suggestions;
	}
}
