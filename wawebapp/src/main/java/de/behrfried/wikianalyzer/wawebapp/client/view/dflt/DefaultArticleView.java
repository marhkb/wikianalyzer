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

import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
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
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

public class DefaultArticleView extends ArticleView {

	private final Presenter presenter;

	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */

	private Label waLabel, yourSearchedArticleLabel, createdLabel, dateCreatedLabel, genArtInfLabel, artAnaLabel;
	private HTMLPane wikiLink, userLink;
	private ComboBoxItem searchBox;
	private DynamicForm searchBoxContainer;
	private IButton translationsAnalyzationButton, revisionAnalyzationButton, authorsAnalyzationButton, categoriesAnalyzationButton,
    articleLengthAnalyzationButton;

	private final ListGrid generalInfoGrid = new ListGrid() {
		@Override
		protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
			if(this.getFieldName(colNum).equals("buttonField")) {
				final String tmpString = record.getAttribute("attributeField");
				if(tmpString.equals("Sprachübersetzungen")) {
					DefaultArticleView.this.translationsAnalyzationButton = new IButton("Zur Analyse");
					DefaultArticleView.this.translationsAnalyzationButton.setIcon("/img/loupeIcon.png");
					DefaultArticleView.this.translationsAnalyzationButton.setAlign(Alignment.CENTER);
					DefaultArticleView.this.bindArticleTranslationsRecord();
					return DefaultArticleView.this.translationsAnalyzationButton;
				} else if(tmpString.equals("Bearbeitungen")) {
					DefaultArticleView.this.revisionAnalyzationButton = new IButton("Zur Analyse");
					DefaultArticleView.this.revisionAnalyzationButton.setIcon("/img/loupeIcon.png");
					DefaultArticleView.this.revisionAnalyzationButton.setAlign(Alignment.CENTER);
					DefaultArticleView.this.bindArticleRevisionsRecord();
					return DefaultArticleView.this.revisionAnalyzationButton;
				} else if(tmpString.equals("Authoren")) {
					DefaultArticleView.this.authorsAnalyzationButton = new IButton("Zur Analyse");
					DefaultArticleView.this.authorsAnalyzationButton.setIcon("/img/loupeIcon.png");
					DefaultArticleView.this.authorsAnalyzationButton.setAlign(Alignment.CENTER);
					DefaultArticleView.this.bindArticleAuthorRecord();
					return DefaultArticleView.this.authorsAnalyzationButton;
				} else if(tmpString.equals("Kategorien")) {
					DefaultArticleView.this.categoriesAnalyzationButton = new IButton("Zur Analyse");
					DefaultArticleView.this.categoriesAnalyzationButton.setIcon("/img/loupeIcon.png");
					DefaultArticleView.this.categoriesAnalyzationButton.setAlign(Alignment.CENTER);
					DefaultArticleView.this.bindArticleCategoriesRecord();
					return DefaultArticleView.this.categoriesAnalyzationButton;
				} else if(tmpString.equals("Länge des Artikels")) {
					DefaultArticleView.this.articleLengthAnalyzationButton = new IButton("Zur Analyse");
					DefaultArticleView.this.articleLengthAnalyzationButton.setIcon("/img/loupeIcon.png");
					DefaultArticleView.this.articleLengthAnalyzationButton.setAlign(Alignment.CENTER);
					DefaultArticleView.this.bindArticleLengthRecord();
					return DefaultArticleView.this.articleLengthAnalyzationButton;
				}
			}
			return null;
		}
	};
	private ListGridField attributeColumn, valueColumn, detailColumn;
	private ListGridRecord translationRecord, revisionRecord, authorsRecord, categoriesRecord, articleLengthRecord;
	private HLayout searchLayout, articleInfoAnalyzationLayout, menuURLLayout, urlLayout;
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
		this.urlLayout = new HLayout();
		this.yourSearchedArticleLabel = new Label();
		this.yourSearchedArticleLabel.setHeight(10);
		this.yourSearchedArticleLabel.setAutoWidth();
		this.yourSearchedArticleLabel.setWrap(false);
		this.wikiLink = new HTMLPane();
		this.dateCreatedLabel = new Label();
		this.userLink = new HTMLPane();
		this.createdLabel = new Label();

		this.urlLayout.addMembers(this.yourSearchedArticleLabel, this.wikiLink, this.dateCreatedLabel, this.createdLabel);

		this.menuURLLayout = new HLayout();
		this.menuURLLayout.addMembers(this.timeMenuButton, this.urlLayout);

		this.attributeColumn = new ListGridField("attributeField", "Attribut");
		this.attributeColumn.setCanEdit(false);
		this.valueColumn = new ListGridField("valueField", "Wert");
		this.valueColumn.setCanEdit(false);
		this.detailColumn = new ListGridField("buttonField", "");
		this.detailColumn.setShowTitle(false);
		this.detailColumn.setCanEdit(false);

		this.translationRecord = new ListGridRecord();
		this.translationRecord.setAttribute(this.attributeColumn.getName(), "Sprachübersetzungen");
		this.revisionRecord = new ListGridRecord();
		this.revisionRecord.setAttribute(this.attributeColumn.getName(), "Bearbeitungen");
		this.authorsRecord = new ListGridRecord();
		this.authorsRecord.setAttribute(this.attributeColumn.getName(), "Authoren");
		this.categoriesRecord = new ListGridRecord();
		this.categoriesRecord.setAttribute(this.attributeColumn.getName(), "Kategorien");
		this.articleLengthRecord = new ListGridRecord();
		this.articleLengthRecord.setAttribute(this.attributeColumn.getName(), "Länge des Artikels");

		this.generalInfoGrid.setShowRecordComponents(true);
		this.generalInfoGrid.setShowRecordComponentsByCell(true);
		this.generalInfoGrid.setShowAllRecords(true);
		this.generalInfoGrid.setFields(this.attributeColumn, this.valueColumn, this.detailColumn);

		this.generalInfoGrid.addData(this.translationRecord);
		this.generalInfoGrid.addData(this.revisionRecord);
		this.generalInfoGrid.addData(this.authorsRecord);
		this.generalInfoGrid.addData(this.categoriesRecord);
		this.generalInfoGrid.addData(this.articleLengthRecord);

		this.artAnaLayout = new VLayout();
		this.artAnaLayout.setWidth("60%");
		this.artAnaLabel = new Label("Article Analyzation");
		this.artAnaLabel.setHeight(10);
		this.artAnaLabel.setWidth100();
		this.artAnaLayout.addMember(this.artAnaLabel);

		this.genInfLayout = new VLayout();
		this.genInfLayout.setWidth("40%");
		this.genInfLayout.setHeight100();
		this.genArtInfLabel = new Label("General Article Infos");
		this.genArtInfLabel.setHeight(10);
		this.genArtInfLabel.setWidth100();
		this.genInfLayout.addMembers(this.genArtInfLabel, this.generalInfoGrid);

		this.articleInfoAnalyzationLayout = new HLayout();
		this.articleInfoAnalyzationLayout.setHeight100();
		this.articleInfoAnalyzationLayout.addMembers(this.genInfLayout, this.artAnaLayout);

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

		this.bindTimeSpanMenu();
		this.bindWikiLinkText();
		this.bindArticleCreateDate();
		this.bindInitialAuthor();
	}

	private void bindSearchBox() {
		this.searchBox.setValue(this.presenter.getArticleTitle());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				DefaultArticleView.this.presenter.setArticleTitle(DefaultArticleView.this.searchBox.getValueAsString());
			}
		});
		this.presenter.articleTitleChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefaultArticleView.this.searchBox.equals(DefaultArticleView.this.presenter.getArticleTitle())) {
					DefaultArticleView.this.searchBox.setValue(DefaultArticleView.this.presenter.getArticleTitle());
				}
			}
		});

		this.searchBox.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					if(DefaultArticleView.this.presenter.getSendCommand().canExecute(null)) {
						DefaultArticleView.this.presenter.getSendCommand().execute(null);
					}
				}
			}
		});

		this.searchBox.setValueMap(this.presenter.getArticleSuggestions());
		this.presenter.articleSuggestionsChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefaultArticleView.this.searchBox.setValueMap(DefaultArticleView.this.presenter.getArticleSuggestions());
				DefaultArticleView.this.searchBox.showPicker();
			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefaultArticleView.this.searchButton.setDisabled(!DefaultArticleView.this.presenter.getSendCommand().canExecute(null));
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				DefaultArticleView.this.presenter.getSendCommand().execute(null);
			}
		});
	}

	private void bindWikiLinkText() {
		if(!this.presenter.getArticleLink().isEmpty()) {
			this.yourSearchedArticleLabel.setTitle("Der von ihnen gesuchte Artikel ");
			this.wikiLink.setTitle(this.presenter.getArticleLink());
			this.userLink.setTitle("");
			this.createdLabel.setTitle(" erstellt.");

		}
		this.presenter.articleLinkChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				wikiLink.setTitle(presenter.getArticleLink());
			}
		});
	}

	private void bindArticleCreateDate() {
		if(this.presenter.getArticleCreationDate() != null) {
			this.dateCreatedLabel.setTitle(" wurde am " + this.presenter.getArticleCreationDate().toString() + " von ");
		}
		this.presenter.articleCreationDateChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				dateCreatedLabel.setTitle(presenter.getArticleCreationDate().toString());
			}
		});
	}

	private void bindInitialAuthor() {
		if(this.presenter.getInitialAuthorLink() != null) {
			this.userLink.setTitle(this.presenter.getInitialAuthorLink());
		}
		
		this.presenter.initialAuthorLinkChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				userLink.setTitle(presenter.getInitialAuthorLink());
			}
		});
	}

	private void bindArticleTranslationsRecord() {
		this.translationsAnalyzationButton.setDisabled(!this.presenter.getAnalyzeTranslationsCommand().canExecute(null));
		this.translationRecord.setAttribute(this.valueColumn.getName(), this.presenter.getNumberOfTranslations());
		this.presenter.numberOfTranslationChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				translationRecord.setAttribute(valueColumn.getName(), presenter.getNumberOfTranslations());
				generalInfoGrid.refreshFields();
			}
		});
		
		this.translationsAnalyzationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DefaultArticleView.this.presenter.getAnalyzeTranslationsCommand().execute(null);
			}
		});
	}

	private void bindArticleRevisionsRecord() {
		this.revisionAnalyzationButton.setDisabled(!this.presenter.getAnalyzeEditsCommand().canExecute(null));
		this.revisionRecord.setAttribute(this.valueColumn.getName(), this.presenter.getNumberOfTranslations());
		this.presenter.numberOfRevisionsChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				revisionRecord.setAttribute(valueColumn.getName(), presenter.getNumberOfTranslations());
				generalInfoGrid.refreshFields();
			}
		});
		
		this.revisionAnalyzationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		DefaultArticleView.this.presenter.getAnalyzeEditsCommand().execute(null);	
			}
		});
	}

	private void bindArticleAuthorRecord() {
		this.authorsAnalyzationButton.setDisabled(!this.presenter.getAnalyzeAuthorsCommand().canExecute(null));
		this.authorsRecord.setAttribute(this.valueColumn.getName(), this.presenter.getNumberOfAuthors());
		this.presenter.numberOfAuthorsChanged().addHandler(new Handler<EventArgs>() {	
			@Override
			public void invoke(Object sender, EventArgs e) {
				authorsRecord.setAttribute(valueColumn.getName(), presenter.getNumberOfAuthors());
				generalInfoGrid.refreshFields();
			}
		});
		
		this.authorsAnalyzationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DefaultArticleView.this.presenter.getAnalyzeAuthorsCommand().execute(null);
			}
		});
	}

	private void bindArticleCategoriesRecord() {
		this.categoriesAnalyzationButton.setDisabled(!this.presenter.getAnalyzeCategoriesCommand().canExecute(null));
		this.categoriesRecord.setAttribute(this.valueColumn.getName(), this.presenter.getArticleCategories());
		this.presenter.articleCategoriesChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				categoriesRecord.setAttribute(valueColumn.getName(), presenter.getArticleCategories());
				generalInfoGrid.refreshFields();
			}
		});
		
		this.categoriesAnalyzationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DefaultArticleView.this.presenter.getAnalyzeCategoriesCommand().execute(null);
			}
		});
	}

	private void bindArticleLengthRecord() {
		this.articleLengthAnalyzationButton.setDisabled(!this.presenter.getAnalyzeArticleWordsCommand().canExecute(null));
		this.articleLengthRecord.setAttribute(this.valueColumn.getName(), this.presenter.getNumberOfArticleWords());
		this.presenter.numberOfArticleWordsChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				articleLengthRecord.setAttribute(valueColumn.getName(), presenter.getNumberOfArticleWords());
				generalInfoGrid.refreshFields();
			}
		});
		
		this.articleLengthAnalyzationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DefaultArticleView.this.presenter.getAnalyzeArticleWordsCommand().execute(null);
			}
		});
	}

	// private void bindGeneralInfoGrid() {
	// final Map<Tuple2<String, String>, Record> recordsO = new
	// HashMap<Tuple2<String, String>, Record>();
	// for(final Tuple2<String, String> t :
	// this.presenter.getArticleInfos()) {
	// final ListGridRecord lsg = new ListGridRecord();
	// lsg.setAttribute("Attribute", t.getItem1());
	// lsg.setAttribute("Value", t.getItem2());
	// this.generalInfoGrid.addData(lsg);
	// recordsO.put(t, lsg);
	// }
	// this.presenter.getArticleInfos().listChanged().addHandler(new
	// Handler<ListChangedEventArgs<Tuple2<String, String>>>() {
	//
	// public void invoke(final Object sender, final
	// ListChangedEventArgs<Tuple2<String, String>> e) {
	// if(e.getListChangedAction() == ListChangedAction.ADD_REMOVE) {
	// if(e.getOldItems() != null) {
	// for(final Tuple2<String, String> t : e.getOldItems()) {
	// DefaultArticleView.this.generalInfoGrid.removeData(recordsO.remove(t));
	// }
	// }
	//
	// if(e.getNewItems() != null) {
	// for(final Tuple2<String, String> t : e.getNewItems()) {
	// final ListGridRecord lsg = new ListGridRecord();
	// lsg.setAttribute("Attribute", t.getItem1());
	// lsg.setAttribute("Value", t.getItem2());
	// DefaultArticleView.this.generalInfoGrid.addData(lsg);
	// recordsO.put(t, lsg);
	// }
	// }
	// } else {
	// DefaultArticleView.this.generalInfoGrid.clear();
	// recordsO.clear();
	// }
	// }
	// });
	// }

	private void bindTimeSpanMenu() {
		this.randomSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.randomSpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.randomSpan.getTitle());
			}
		});
		this.hourSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.hourSpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.hourSpan.getTitle());
			}
		});
		this.daySpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.daySpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.daySpan.getTitle());
			}
		});
		this.weekSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.weekSpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.weekSpan.getTitle());
			}
		});
		this.monthSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.monthSpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.monthSpan.getTitle());
			}
		});
		this.yearSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.yearSpan.setChecked(true);
				DefaultArticleView.this.timeMenuButton.setTitle(DefaultArticleView.this.yearSpan.getTitle());
			}
		});
		this.chooseSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefaultArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefaultArticleView.this.chooseSpan.setChecked(true);
			}
		});

	}

	@Override
	public String getName() {
		return "Article";
	}
}
