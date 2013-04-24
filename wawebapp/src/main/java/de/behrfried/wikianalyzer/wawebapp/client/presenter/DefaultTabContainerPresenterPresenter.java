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

import com.google.inject.Inject;

import de.behrfried.wikianalyzer.wawebapp.client.view.TabContainerView;

/**
 * Default implementation for {@link TabContainerPresenter}.
 * @author marcus
 *
 */
public class DefaultTabContainerPresenterPresenter implements TabContainerPresenter {

    /**
     * the {@link DefaultTabContainerPresenterPresenter}'s {@link TabContainerView}
     */
    private final TabContainerView view;
    
    private final ArticlePresenter articlePresenter;
    
    /**
     * Creates an instance of {@link DefaultTabContainerPresenterPresenter}. All arguments are injected by Gin.
     * @param view the {@link DefaultTabContainerPresenterPresenter}'s {@link TabContainerView}
     * @throws IllegalArgumentException if view == null
     */
    @Inject
    public DefaultTabContainerPresenterPresenter(final TabContainerView view, final ArticlePresenter articlePresenter) throws IllegalArgumentException {
	if(view == null) {
	    throw new IllegalArgumentException("view == null");
	}
	this.view = view;
	this.articlePresenter = articlePresenter;
    }

    /**
     * @see Presenter
     */
    public void init() {
	this.view.init();
	this.articlePresenter.init();
	
	//TODO add handlers
    }

    /**
     * @see Presenter
     */
    public void dispose() {
	this.view.dispose();
	
	//TODO remove handlers
    }
}
