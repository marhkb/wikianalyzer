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
 * 
 * @author marcus
 * 
 */
public class DefaultTabContainerPresenter implements TabContainerPresenter {

	/**
	 * the {@link DefaultTabContainerPresenter}'s {@link TabContainerView}
	 */
	private final TabContainerView view;

	private final ArticlePresenter articlePresenter;
	private final UserPresenter userPresenter;

	/**
	 * Creates an instance of {@link DefaultTabContainerPresenter}. All
	 * arguments are injected by Gin.
	 * 
	 * @param view
	 *            the {@link DefaultTabContainerPresenter}'s
	 *            {@link TabContainerView}
	 * @throws IllegalArgumentException
	 *             if view == null
	 */
	@Inject
	public DefaultTabContainerPresenter(final TabContainerView view,
			final ArticlePresenter articlePresenter,
			final UserPresenter userPresenter) throws IllegalArgumentException {
		if (view == null) {
			throw new IllegalArgumentException("view == null");
		}
		this.view = view;
		this.articlePresenter = articlePresenter;
		this.userPresenter = userPresenter;
	}

	/**
	 * @see Presenter
	 */
	public void init() {
		this.view.init();
		this.articlePresenter.init();
		this.userPresenter.init();
	}

	/**
	 * @see Presenter
	 */
	public void dispose() {
		this.view.dispose();

		// TODO remove handlers
	}
}
