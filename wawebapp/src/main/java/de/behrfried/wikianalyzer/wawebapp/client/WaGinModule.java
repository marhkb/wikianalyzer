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
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultShellViewPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultUserComparisonPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultUserPresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.DefShellView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article.DefArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user.DefExpertSearchView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user.DefUserView;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.usercomparison.DefUserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserView;
import de.behrfried.wikianalyzer.wawebapp.client.view.usercomparison.UserComparisonView;

/**
 * Main Module in the web application. Defines which implementation is chosen
 * for an interface.
 * 
 * @author marcus
 * 
 */
public class WaGinModule extends AbstractGinModule {

	/**
	 * Configures the Module by defining implementations for interfaces.
	 */
	@Override
	protected void configure() {
		/*
		 * bind presenters
		 */
		this.bind(ShellView.Presenter.class).to(DefaultShellViewPresenter.class).in(Singleton.class);
		this.bind(ArticleView.Presenter.class).to(MockArticlePresenter.class).in(Singleton.class);
		this.bind(UserView.Presenter.class).to(DefaultUserPresenter.class).in(Singleton.class);
		this.bind(UserComparisonView.Presenter.class).to(DefaultUserComparisonPresenter.class).in(Singleton.class);
		this.bind(ExpertSearchView.Presenter.class).to(DefaultExpertSearchPresenter.class).in(Singleton.class);

		/*
		 * bind views
		 */
		this.bind(ShellView.class).to(DefShellView.class).in(Singleton.class);
		this.bind(ArticleView.class).to(DefArticleView.class).in(Singleton.class);
		this.bind(UserView.class).to(DefUserView.class).in(Singleton.class);
		this.bind(UserComparisonView.class).to(DefUserComparisonView.class).in(Singleton.class);
		this.bind(ExpertSearchView.class).to(DefExpertSearchView.class).in(Singleton.class);
	}
}
