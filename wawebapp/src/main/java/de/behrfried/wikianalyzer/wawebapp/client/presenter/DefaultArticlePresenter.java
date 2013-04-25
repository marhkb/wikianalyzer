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
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

/**
 * Default implementation for {@link ArticlePresenter}.
 * @author marcus
 *
 */
public class DefaultArticlePresenter implements ArticlePresenter {
    
    /**
     * The {@link DefaultArticlePresenter}'s {@link ArticleView}
     */
    private final ArticleView view;
    
    /**
     * Creates a {@link DefaultArticlePresenter}. All parameters are injected by Gin.
     * @param view the {@link DefaultArticlePresenter}'s {@link ArticleView}
     * @throws IllegalArgumentException if view == null
     */
    @Inject
    public DefaultArticlePresenter(ArticleView view) throws IllegalArgumentException {
	if(view == null) {
	    throw new IllegalArgumentException("view == null");
	}
	this.view = view;
    }
    
    /**
     * @see Presenter
     */
    public void init() {
	this.view.init();
    }
    
    /**
     * @see Presenter
     */
    public void dispose() {
	this.view.dispose();
    }
    
}