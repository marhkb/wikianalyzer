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

import java.util.HashMap;
import java.util.Map;
import com.google.inject.Inject;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Button;
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
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
=======
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
import de.behrfried.wikianalyzer.util.data.Tuple2;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.event.Handler;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs;
import de.behrfried.wikianalyzer.util.list.ListChangedEventArgs.ListChangedAction;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

public class DefaultArticleView extends ArticleView {

	private final Presenter presenter;

	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */

	private Label waLabel, yourSearchedArticleLabel, genArtInfLabel, artAnaLabel;
	private HTMLPane wikiLink;
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
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
		this.wikiLink = new HTMLPane();
=======
		// TODO this.wikiLink = new LinkItem("www.google.de");
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
		this.menuURLLayout = new HLayout();
		this.menuURLLayout.addMembers(this.timeMenuButton, this.yourSearchedArticleLabel, this.wikiLink);

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
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
		this.articleInfoAnalyzationLayout.addMembers(this.genInfLayout, this.artAnaLayout);
=======
		this.articleInfoAnalyzationLayout.addMembers(this.artAnaLayout, this.genInfLayout);
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java

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
		this.bindSearchButton();
		this.bindTimeSpanMenu();

	}

	private void bindSearchBox() {
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
		this.searchBox.setValue(this.presenter.getArticleName());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				DefaultArticleView.this.presenter.setArticleName(searchBox.getValueAsString());
			}
		});
		this.presenter.articleNameChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(Object sender, EventArgs e) {
				if(!searchBox.equals(presenter.getArticleName())) {
					searchBox.setValue(presenter.getArticleName());
				}
=======
		this.searchBox.setValue(this.presenter.getArticleTitle());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				DefaultArticleView.this.presenter.setArticleTitle(DefaultArticleView.this.searchBox.getValueAsString());
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
			}
		});
		this.presenter.articleTitleChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefaultArticleView.this.searchBox.getValueAsString().equals(DefaultArticleView.this.presenter.getArticleTitle())) {
					DefaultArticleView.this.searchBox.setValue(DefaultArticleView.this.presenter.getArticleTitle());
				}
			}
		});

		this.searchBox.addKeyUpHandler(new KeyUpHandler() {

<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
			public void onKeyUp(KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					if(presenter.getSendCommand().canExecute(null)) {
						presenter.getSendCommand().execute(null);
=======
			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					if(DefaultArticleView.this.presenter.getSendCommand().canExecute(null)) {
						DefaultArticleView.this.presenter.getSendCommand().execute(null);
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
					}
				}
			}
		});

		this.searchBox.setValueMap(this.presenter.getSuggestions());
		this.presenter.suggestionsChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefaultArticleView.this.searchBox.setValueMap(DefaultArticleView.this.presenter.getSuggestions());
				DefaultArticleView.this.searchBox.showPicker();
			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
			public void invoke(Object sender, EventArgs e) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null));
=======
			public void invoke(final Object sender, final EventArgs e) {
				DefaultArticleView.this.searchButton.setDisabled(!DefaultArticleView.this.presenter.getSendCommand().canExecute(null));
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
			public void onClick(ClickEvent event) {
				presenter.getSendCommand().execute(null);
=======
			public void onClick(final ClickEvent event) {
				DefaultArticleView.this.presenter.getSendCommand().execute(null);
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
			}
		});
	}

	private void bindGeneralInfoGrid() {
		final Map<Tuple2<String, String>, Record> recordsO = new HashMap<Tuple2<String, String>, Record>();
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
		for(Tuple2<String, String> t : this.presenter.getArticleInfos()) {
			ListGridRecord lsg = new ListGridRecord();
=======
		for(final Tuple2<String, String> t : this.presenter.getArticleInfos()) {
			final ListGridRecord lsg = new ListGridRecord();
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
			lsg.setAttribute("Attribute", t.getItem1());
			lsg.setAttribute("Value", t.getItem2());
			this.generalInfoGrid.addData(lsg);
			recordsO.put(t, lsg);
		}
		this.presenter.getArticleInfos().listChanged().addHandler(new Handler<ListChangedEventArgs<Tuple2<String, String>>>() {

<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
			public void invoke(Object sender, ListChangedEventArgs<Tuple2<String, String>> e) {
				if(e.getListChangedType() == ListChangedType.ADD_REMOVE) {
					if(e.getOldItems() != null) {
						for(Tuple2<String, String> t : e.getOldItems()) {
							generalInfoGrid.removeData(recordsO.remove(t));
=======
			public void invoke(final Object sender, final ListChangedEventArgs<Tuple2<String, String>> e) {
				if(e.getListChangedAction() == ListChangedAction.ADD_REMOVE) {
					if(e.getOldItems() != null) {
						for(final Tuple2<String, String> t : e.getOldItems()) {
							DefaultArticleView.this.generalInfoGrid.removeData(recordsO.remove(t));
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
						}
					}

					if(e.getNewItems() != null) {
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
						for(Tuple2<String, String> t : e.getNewItems()) {
							ListGridRecord lsg = new ListGridRecord();
							lsg.setAttribute("Attribute", t.getItem1());
							lsg.setAttribute("Value", t.getItem2());
							generalInfoGrid.addData(lsg);
=======
						for(final Tuple2<String, String> t : e.getNewItems()) {
							final ListGridRecord lsg = new ListGridRecord();
							lsg.setAttribute("Attribute", t.getItem1());
							lsg.setAttribute("Value", t.getItem2());
							DefaultArticleView.this.generalInfoGrid.addData(lsg);
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
							recordsO.put(t, lsg);
						}
					}
				} else {
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
					generalInfoGrid.clear();
=======
					DefaultArticleView.this.generalInfoGrid.clear();
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
					recordsO.clear();
				}
			}
		});
<<<<<<< HEAD:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/DefaultArticleView.java
	}

	private void bindTimeSpanMenu() {
		this.randomSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				randomSpan.setChecked(true);
				timeMenuButton.setTitle(randomSpan.getTitle());
			}
		});
		this.hourSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				hourSpan.setChecked(true);
				timeMenuButton.setTitle(hourSpan.getTitle());
			}
		});
		this.daySpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				daySpan.setChecked(true);
				timeMenuButton.setTitle(daySpan.getTitle());
			}
		});
		this.weekSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				weekSpan.setChecked(true);
				timeMenuButton.setTitle(weekSpan.getTitle());
			}
		});
		this.monthSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				monthSpan.setChecked(true);
				timeMenuButton.setTitle(monthSpan.getTitle());
			}
		});
		this.yearSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				yearSpan.setChecked(true);
				timeMenuButton.setTitle(yearSpan.getTitle());
			}
		});
		this.chooseSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				for(MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				chooseSpan.setChecked(true);
				
			}
		});
		
=======
>>>>>>> 9565e7e66fcd14eb590d7404e4f509939388252e:wawebapp/src/main/java/de/behrfried/wikianalyzer/wawebapp/client/view/dflt/DefaultArticleView.java
	}

	@Override
	public String getName() {
		return "Article";
	}
}
