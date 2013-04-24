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
 
package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.inject.Inject;

public class DefaultArticleView implements ArticleView {
	private Label waLabel;
	private SuggestBox searchBox;
	private Button searchButton;
	private MenuBar menuBar;
	private StackLayoutPanel wikiPrevPanel;

	public void init() {
		this.waLabel = new Label();
		this.searchBox = new SuggestBox();
		this.searchButton = new Button();
		this.menuBar = new MenuBar();
		this.wikiPrevPanel = new StackLayoutPanel(Style.Unit.PX);
	}

	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */
	private final StartPageView parentView;

	@Inject
	public DefaultArticleView(StartPageView parentView) {
		this.parentView = parentView;
	}

	/**
	 * @see View
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Label getWaLabel() {
		return waLabel;
	}

	public SuggestBox getSearchBox() {
		return searchBox;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public StackLayoutPanel getWikiPrevPanel() {
		return wikiPrevPanel;
	}

}
