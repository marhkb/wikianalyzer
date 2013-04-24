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
 
package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.ArticlePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultArticlePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultStartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.StartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.DefaultArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.TabContainerView;
import de.behrfried.wikianalyzer.wawebapp.client.view.StartPageView;
/**
 * Main Module in the web application.
 * Defines which implementation is chosen for an interface.  
 * @author marcus
 *
 */
public class WaGinModule extends AbstractGinModule {

    /**
     * Configures the Module by defining implementations forinterfaces.
     */
    @Override
    protected void configure() {
	
	/*
	 * 
	 */
	this.bind(ArticleView.class).to(DefaultArticleView.class).in(Singleton.class);
	
	
	this.bind(ArticlePresenter.class).to(DefaultArticlePresenter.class).in(Singleton.class);
	this.bind(StartPageView.class).to(TabContainerView.class).in(Singleton.class);
	this.bind(StartPagePresenter.class).to(DefaultStartPagePresenter.class).in(Singleton.class);
    }
    
}
