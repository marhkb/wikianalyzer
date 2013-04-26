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

import de.behrfried.wikianalyzer.wawebapp.client.event.Event;
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
	private final Event<FieldChangedEventArgs> fieldChangedEvent = new Event<FieldChangedEventArgs>();

	@Inject
	public DefaultArticlePresenter(final MainServiceAsync mainService) {
		this.mainService = mainService;
		
	}
	
	public void searchArticle(String article) {
		this.mainService.getArticle(article, new AsyncCallback<String>() {
			
			public void onSuccess(String result) {
				
			}
			
			public void onFailure(Throwable caught) {
				
			}
		});
	}

	
	public Event<FieldChangedEventArgs> getFieldChangedEvent() {
		return this.fieldChangedEvent;
	}

	public void getArticleHtml() {
		// TODO Auto-generated method stub
		
	}
}