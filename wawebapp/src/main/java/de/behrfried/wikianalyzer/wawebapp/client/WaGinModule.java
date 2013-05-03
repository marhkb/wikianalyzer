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
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultShellViewPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultUserComparisonPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultUserPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.service.JQueryWikiAccess;
import de.behrfried.wikianalyzer.wawebapp.client.service.WikiAccess;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView;
import de.behrfried.wikianalyzer.wawebapp.client.view.UserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.client.view.UserView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.DefaultArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.DefaultShellView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.DefaultUserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.DefaultUserView;

/**
 * Main Module in the web application. Defines which implementation is chosen
 * for an interface.
 * 
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
		 * bind services
		 */
		this.bind(WikiAccess.class).to(JQueryWikiAccess.class).in(Singleton.class);

		/*
		 * bind presenters
		 */
		this.bind(ShellView.Presenter.class).to(DefaultShellViewPresenter.class).in(Singleton.class);
		this.bind(ArticleView.Presenter.class).to(MockArticlePresenter.class).in(Singleton.class);
		this.bind(UserView.Presenter.class).to(DefaultUserPresenter.class).in(Singleton.class);
		this.bind(UserComparisonView.Presenter.class).to(DefaultUserComparisonPresenter.class).in(Singleton.class);

		/*
		 * bind views
		 */
		this.bind(ShellView.class).to(DefaultShellView.class).in(Singleton.class);
		this.bind(ArticleView.class).to(DefaultArticleView.class).in(Singleton.class);
		this.bind(UserView.class).to(DefaultUserView.class).in(Singleton.class);
		this.bind(UserComparisonView.class).to(DefaultUserComparisonView.class).in(Singleton.class);

	}

}
