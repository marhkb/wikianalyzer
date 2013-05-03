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

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.event.Handler;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs.ListChangedType;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView.Presenter;

public class DefaultArticleView extends ArticleView {

	private final Presenter presenter;

	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */

	private Label waLabel, yourSearchedArticleLabel, urlLabel, genArtInfLabel, artAnaLabel;
	private LinkItem wikiLink;
	private ComboBoxItem searchBox;
	private DynamicForm searchBoxContainer;
	private ListGrid generalInfoGrid;
	private ListGridField attributeColumn, valueColumn;
	private HLayout searchLayout, articleInfoAnalyzationLayout, menuURLLayout;
	private VLayout siteLayoutContainer, genInfLayout, artAnaLayout;
	private Button searchButton;
	private IMenuButton timeMenuButton;
	private Menu timeSpanMenu;
	private MenuItem randomSpan, hourSpan, daySpan, weekSpan, monthSpan, yearSpan, chooseSpan;

	private final Messages messages;

	@Inject
	public DefaultArticleView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.init();
	}

	private void init() {
		this.waLabel = new Label("WIKIAnalyzer");
		this.waLabel.setHeight100();
		this.waLabel.setMargin(10);
		this.searchBox = new ComboBoxItem();
		this.searchBox.setWidth(200);
		this.searchBox.setShowTitle(false);
		this.searchBoxContainer = new DynamicForm();
		this.searchBoxContainer.setBackgroundColor("white");
		this.searchBoxContainer.setItems(this.searchBox);
		this.searchButton = new Button(this.messages.searchButton());
		this.searchLayout = new HLayout();
		this.searchLayout.setMembersMargin(3);
		this.searchLayout.setHeight(30);
		this.searchLayout.addMember(this.waLabel);
		this.searchLayout.addMember(this.searchBoxContainer);
		this.searchLayout.addMember(this.searchButton);

		this.timeSpanMenu = new Menu();
		this.randomSpan = new MenuItem("random time");
		this.randomSpan.setChecked(true);
		this.timeSpanMenu.addItem(this.randomSpan);
		this.hourSpan = new MenuItem("last hour");
		this.timeSpanMenu.addItem(this.hourSpan);
		this.daySpan = new MenuItem("last day");
		this.timeSpanMenu.addItem(this.daySpan);
		this.weekSpan = new MenuItem("last week");
		this.timeSpanMenu.addItem(this.weekSpan);
		this.monthSpan = new MenuItem("last month");
		this.timeSpanMenu.addItem(this.monthSpan);
		this.yearSpan = new MenuItem("last year");
		this.timeSpanMenu.addItem(this.yearSpan);
		this.chooseSpan = new MenuItem("choose timespan");
		this.timeSpanMenu.addItem(this.chooseSpan);
		this.timeMenuButton = new IMenuButton(this.randomSpan.getTitle(), this.timeSpanMenu);
		this.yourSearchedArticleLabel = new Label("Ihr gesuchter Artikel war: ");
		// TODO this.wikiLink = new LinkItem("www.google.de");
		this.menuURLLayout = new HLayout();
		this.menuURLLayout.addMembers(this.timeMenuButton, this.yourSearchedArticleLabel);

		this.attributeColumn = new ListGridField("Attribute");
		this.attributeColumn.setCanEdit(false);
		this.valueColumn = new ListGridField("Value");
		// this.valueColumn.setC
		this.valueColumn.setCanEdit(false);
		this.generalInfoGrid = new ListGrid();

		this.generalInfoGrid.setFields(this.attributeColumn, this.valueColumn);

		this.artAnaLayout = new VLayout();
		this.artAnaLayout.setWidth100();
		this.artAnaLabel = new Label("Article Analyzation");
		this.artAnaLabel.setHeight(10);
		this.artAnaLabel.setWidth100();
		this.artAnaLayout.addMember(this.artAnaLabel);

		this.genInfLayout = new VLayout();
		this.genInfLayout.setWidth("33%");
		this.genInfLayout.setHeight100();
		this.genArtInfLabel = new Label("General Article Infos");
		this.genArtInfLabel.setHeight(10);
		this.genArtInfLabel.setWidth100();
		this.genInfLayout.addMembers(this.genArtInfLabel, this.generalInfoGrid);

		this.articleInfoAnalyzationLayout = new HLayout();
		this.articleInfoAnalyzationLayout.addMembers(this.artAnaLayout, this.genInfLayout);

		this.siteLayoutContainer = new VLayout();
		this.siteLayoutContainer.setWidth100();
		this.siteLayoutContainer.setHeight100();
		this.siteLayoutContainer.addMembers(this.searchLayout, this.menuURLLayout, this.articleInfoAnalyzationLayout);

		this.addChild(this.siteLayoutContainer);

		this.bind();

	}

	private void bind() {
		this.bindSearchBox();
		this.bindSearchButton();
		this.bindGeneralInfoGrid();

	}

	private void bindSearchBox() {
		this.searchBox.setValue(this.presenter.getArticleName());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				DefaultArticleView.this.presenter.setArticleName(searchBox.getValueAsString());
			}
		});
		this.presenter.articleNameChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(Object sender, EventArgs e) {
				if(!searchBox.getValueAsString().equals(presenter.getArticleName())) {
					searchBox.setValue(presenter.getArticleName());
				}
			}
		});
		
		this.searchBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					if(presenter.getSendCommand().canExecute(null)) {
						presenter.getSendCommand().execute(null);
					}
				}
			}
		});
		
		this.searchBox.setValueMap(this.presenter.getSuggestions());
		this.presenter.suggestionsChanged().addHandler(new Handler<EventArgs>() {
			public void invoke(Object sender, EventArgs e) {
				searchBox.setValueMap(presenter.getSuggestions());
				searchBox.showPicker();
			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(Object sender, EventArgs e) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null));
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				presenter.getSendCommand().execute(null);
			}
		});
	}
	
	private void bindGeneralInfoGrid() {

		final Map<Tuple2<String, String>, Record> recordsO = new HashMap<Tuple2<String, String>, Record>();
		for(Tuple2<String, String> t : this.presenter.getArticleInfos()) {
			ListGridRecord lsg = new ListGridRecord();
			lsg.setAttribute("Attribute", t.getItem1());
			lsg.setAttribute("Value", t.getItem2());
			this.generalInfoGrid.addData(lsg);
			recordsO.put(t, lsg);
		}
		this.presenter.getArticleInfos().listChanged().addHandler(new Handler<ListChangedEventArgs<Tuple2<String, String>>>() {

			public void invoke(Object sender, ListChangedEventArgs<Tuple2<String, String>> e) {
				if(e.getListChangedType() == ListChangedType.ADD_REMOVE) {
					if(e.getOldItems() != null) {
						for(Tuple2<String, String> t : e.getOldItems()) {
							generalInfoGrid.removeData(recordsO.remove(t));
						}
					}

					if(e.getNewItems() != null) {
						for(Tuple2<String, String> t : e.getNewItems()) {
							ListGridRecord lsg = new ListGridRecord();
							lsg.setAttribute("Attribute", t.getItem1());
							lsg.setAttribute("Value", t.getItem2());
							generalInfoGrid.addData(lsg);
							recordsO.put(t, lsg);
						}
					}
				} else {
					generalInfoGrid.clear();
					recordsO.clear();
				}
			}
		});
	}

	@Override
	public String getName() {
		return "Article";
	}
}
