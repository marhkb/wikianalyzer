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

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
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
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView;

public class DefArticleView extends ArticleView {

	private final Presenter presenter;
	private final ArticleAnaView articleAnaView;

	private final Messages messages;

	/**
	 * the parent element of the {@link DefArticleView} where the widgets has to
	 * be put in.
	 */

	private Label waLabel, genArtInfLabel, artAnaLabel;
	private HTMLFlow wikiLink, userLink;
	private ComboBoxItem searchBox;
	private DynamicForm searchBoxContainer;

	private final ListGrid generalInfoGrid = new ListGrid() {

        @Override
        protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
            final String tmpString = record.getAttribute("attributeField");
            if(this.getFieldName(colNum).equals("valueField")) {
                if(tmpString.equals("Link zur gesuchten Seite:")) {
                    DefArticleView.this.wikiLink = new HTMLFlow();
                    DefArticleView.this.bindWikiLinkFlowRecord();
                    return DefArticleView.this.wikiLink;
                } else if(tmpString.equals("Initialer Author:")) {
                    DefArticleView.this.userLink = new HTMLFlow();
                    DefArticleView.this.bindUserLinkRecord();
                    return DefArticleView.this.userLink;
                }
            }
            return null;
        }
    }; 
	private ListGridField attributeColumn, valueColumn;
	private ListGridRecord revisionRecord, authorsRecord, categoriesRecord, articleLengthRecord, pictureRecord, infoBoxRecord, needsEditRecord,
	        initialAuthorRecord, linkRecord, creationDateRecord;
	private HLayout searchLayout, articleInfoAnalyzationLayout;
	private VLayout siteLayoutContainer, genInfLayout, artAnaLayout;
	private Button searchButton;
	private IMenuButton timeMenuButton;
	private Menu timeSpanMenu;
	private MenuItem randomSpan, hourSpan, daySpan, weekSpan, monthSpan, yearSpan, chooseSpan;
	private DefArticleAnaView defArticleAnaView;

	@Inject
	public DefArticleView(final Presenter presenter, final Messages messages, final ArticleAnaView articleAnaView)
	        throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.articleAnaView = articleAnaView;

		this.init();
	}

	private void init() {
		this.waLabel = new Label("WIKIAnalyzer");
		this.waLabel.setHeight100();
		this.waLabel.setMargin(10);
		this.searchBox = new ComboBoxItem();
		this.searchBox.setWidth(200);
		this.searchBox.setShowTitle(false);
        this.searchBox.setShowPickerIcon(false);
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

		this.attributeColumn = new ListGridField("attributeField", "Attribut");
		this.attributeColumn.setCanEdit(false);
		this.attributeColumn.setCanSort(false);
		this.valueColumn = new ListGridField("valueField", "Wert");
		this.valueColumn.setCanEdit(false);
		this.valueColumn.setCanSort(false);

		this.linkRecord = new ListGridRecord();
		this.linkRecord.setAttribute(this.attributeColumn.getName(), "Link zur gesuchten Seite:");
		this.creationDateRecord = new ListGridRecord();
		this.creationDateRecord.setAttribute(this.attributeColumn.getName(), "Erstellungsdatum:");
		this.initialAuthorRecord = new ListGridRecord();
		this.initialAuthorRecord.setAttribute(this.attributeColumn.getName(), "Initialer Author:");
		this.infoBoxRecord = new ListGridRecord();
		this.infoBoxRecord.setAttribute(this.attributeColumn.getName(), "InfoBox:");
		this.pictureRecord = new ListGridRecord();
		this.pictureRecord.setAttribute(this.attributeColumn.getName(), "Bilder:");
		this.needsEditRecord = new ListGridRecord();
		this.needsEditRecord.setAttribute(this.attributeColumn.getName(), "Benötigt Quellen/Input:");
		this.revisionRecord = new ListGridRecord();
		this.revisionRecord.setAttribute(this.attributeColumn.getName(), "Bearbeitungen:");
		this.authorsRecord = new ListGridRecord();
		this.authorsRecord.setAttribute(this.attributeColumn.getName(), "Anzahl Authoren:");
		this.categoriesRecord = new ListGridRecord();
		this.categoriesRecord.setAttribute(this.attributeColumn.getName(), "Kategorien:");
		this.articleLengthRecord = new ListGridRecord();
		this.articleLengthRecord.setAttribute(this.attributeColumn.getName(), "Länge des Artikels:");

		this.generalInfoGrid.setShowRecordComponents(true);
		this.generalInfoGrid.setShowRecordComponentsByCell(true);
		this.generalInfoGrid.setShowAllRecords(true);
		this.generalInfoGrid.setCanResizeFields(false);
		this.generalInfoGrid.setShowHeaderMenuButton(false);
		this.generalInfoGrid.setFields(this.attributeColumn, this.valueColumn);

		this.generalInfoGrid.addData(this.linkRecord);
		this.generalInfoGrid.addData(this.creationDateRecord);
		this.generalInfoGrid.addData(this.initialAuthorRecord);
		this.generalInfoGrid.addData(this.infoBoxRecord);
		this.generalInfoGrid.addData(this.pictureRecord);
		this.generalInfoGrid.addData(this.needsEditRecord);

		this.generalInfoGrid.addData(this.revisionRecord);
		this.generalInfoGrid.addData(this.authorsRecord);
		this.generalInfoGrid.addData(this.categoriesRecord);
		this.generalInfoGrid.addData(this.articleLengthRecord);

		this.artAnaLabel = new Label("Article Analyzation");
		this.artAnaLabel.setHeight(10);
		this.artAnaLabel.setWidth100();
		this.artAnaLayout = new VLayout();
		this.artAnaLayout.setOverflow(Overflow.AUTO);
		this.artAnaLayout.setWidth("70%");
		this.artAnaLayout.addMember(this.artAnaLabel);


		this.defArticleAnaView = new DefArticleAnaView(presenter, messages);
		this.artAnaLayout.addMember(defArticleAnaView);


		this.genInfLayout = new VLayout();
		this.genInfLayout.setWidth("30%");
		this.genInfLayout.setHeight100();
		this.genArtInfLabel = new Label("General Article Infos");
		this.genArtInfLabel.setHeight(10);
		this.genArtInfLabel.setWidth100();
		this.genInfLayout.addMembers(this.genArtInfLabel, this.generalInfoGrid);

		this.articleInfoAnalyzationLayout = new HLayout();
		this.articleInfoAnalyzationLayout.addMembers(this.genInfLayout, this.artAnaLayout);

		this.siteLayoutContainer = new VLayout();
		this.siteLayoutContainer.setWidth100();
		this.siteLayoutContainer.setHeight100();
		this.siteLayoutContainer.addMembers(this.searchLayout, this.timeMenuButton, this.articleInfoAnalyzationLayout);
		this.addChild(this.siteLayoutContainer);

		this.bind();
	}

	private void bind() {
		this.bindSearchBox();
		this.bindSearchButton();
		this.bindTimeSpanMenu();
		this.bindNeedsEditRecord();
		this.bindInfoBoxRecord();
		this.bindPictureRecord();
		this.bindArticleCreationDateRecord();
		this.bindArticleRevisionsRecord();
		this.bindArticleAuthorsRecord();
		this.bindArticleCategoriesRecord();
		this.bindArticleLengthRecord();
		this.bindArticleInfoAnalyzationLayout();
	}

	private void bindArticleInfoAnalyzationLayout() {
		//articleInfoAnalyzationLayout.setVisible(presenter.getSearchStatus());
		this.presenter.searchStatusChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs eventArgs) {
				articleInfoAnalyzationLayout.setVisible(presenter.getSearchStatus());
			}
		});
	}


	private void bindSearchBox() {
		this.searchBox.setValue(this.presenter.getArticleTitle());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				DefArticleView.this.presenter.setArticleTitle(DefArticleView.this.searchBox.getValueAsString());
			}
		});
		this.presenter.articleTitleChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefArticleView.this.searchBox.equals(DefArticleView.this.presenter.getArticleTitle())) {
					DefArticleView.this.searchBox.setValue(DefArticleView.this.presenter.getArticleTitle());
				}
			}
		});

		this.searchBox.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName() != null && event.getKeyName().equals("Enter")) {
					if(DefArticleView.this.presenter.getSendCommand().canExecute(null)) {
						DefArticleView.this.presenter.getSendCommand().execute(null);
					}
				}
			}
		});

		this.searchBox.setValueMap(this.presenter.getArticleSuggestions());
		this.presenter.articleSuggestionsChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefArticleView.this.searchBox.setValueMap(DefArticleView.this.presenter.getArticleSuggestions());
                    searchBox.showPicker();
			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null));
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				presenter.getSendCommand().execute(null);
			}
		});
	}

	private void bindTimeSpanMenu() {
		this.randomSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				randomSpan.setChecked(true);
				timeMenuButton.setTitle(randomSpan.getTitle());
			}
		});
		this.hourSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefArticleView.this.hourSpan.setChecked(true);
				DefArticleView.this.timeMenuButton.setTitle(hourSpan.getTitle());
			}
		});
		this.daySpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefArticleView.this.daySpan.setChecked(true);
				DefArticleView.this.timeMenuButton.setTitle(DefArticleView.this.daySpan.getTitle());
			}
		});
		this.weekSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefArticleView.this.weekSpan.setChecked(true);
				DefArticleView.this.timeMenuButton.setTitle(DefArticleView.this.weekSpan.getTitle());
			}
		});
		this.monthSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				monthSpan.setChecked(true);
				timeMenuButton.setTitle(monthSpan.getTitle());
			}
		});
		this.yearSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				yearSpan.setChecked(true);
				timeMenuButton.setTitle(yearSpan.getTitle());
			}
		});
		this.chooseSpan.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			public void onClick(final MenuItemClickEvent event) {
				for(final MenuItem mi : DefArticleView.this.timeSpanMenu.getItems()) {
					mi.setChecked(false);
				}
				DefArticleView.this.chooseSpan.setChecked(true);
			}
		});
	}

	private void bindArticleRevisionsRecord() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs e) {
						revisionRecord.setAttribute(
								valueColumn.getName(),
								presenter.getArticleInfo().getRevisions().size()
						);
						generalInfoGrid.refreshFields();
					}
				}
		);
	}

	private void bindArticleAuthorsRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				authorsRecord.setAttribute(valueColumn.getName(), presenter.getArticleInfo().getAuthorsAndCommits()
						.size());
				generalInfoGrid.refreshFields();
			}
		});	}

	private void bindArticleCategoriesRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				categoriesRecord.setAttribute(valueColumn.getName(), presenter.getArticleInfo().getCategories());
				generalInfoGrid.refreshFields();
			}
		});
	}

	private void bindWikiLinkFlowRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				wikiLink.setContents("<a href='" + presenter.getArticleInfo().getArticleLink() + "' target='_blank'>" +
									 presenter.getArticleInfo().getTitle() +  "</a>");
				generalInfoGrid.refreshFields();
			}
		});
	}

	private void bindUserLinkRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userLink.setContents(
						"<a href=\"" + presenter.getArticleInfo().getInitialAuthorLink() + "\" target=\"_blank\">" +
						presenter.getArticleInfo().getInitialAuthor() + "</a>"
				);
				userLink.setTitle(presenter.getArticleInfo().getInitialAuthorLink());
				generalInfoGrid.refreshFields();
			}
		});
	}

	private void bindArticleCreationDateRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				creationDateRecord.setAttribute(valueColumn.getName(), presenter.getArticleInfo().getCreationDate());
				generalInfoGrid.refreshFields();
			}
		});
	}

	private void bindInfoBoxRecord() {
//		if(this.presenter.getHasInfoBox()) {
//			this.infoBoxRecord.setAttribute(this.valueColumn.getName(), "Ja");
//		} else {
//			this.infoBoxRecord.setAttribute(this.valueColumn.getName(), "Nein");
//		}
//		this.presenter.infoBoxChanged().addHandler(new Handler<EventArgs>() {
//
//			@Override
//			public void invoke(Object sender, EventArgs e) {
//				if(presenter.getHasInfoBox()) {
//					infoBoxRecord.setAttribute(valueColumn.getName(), "Ja");
//				} else {
//					infoBoxRecord.setAttribute(valueColumn.getName(), "Nein");
//				}
//				generalInfoGrid.refreshFields();
//			}
//		});
	}

	private void bindPictureRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				pictureRecord.setAttribute(valueColumn.getName(), presenter.getArticleInfo().getImages());
				generalInfoGrid.refreshFields();
			}
		});
	}

	private void bindNeedsEditRecord() {
//		if(this.presenter.articleNeedsEdit()) {
//			this.infoBoxRecord.setAttribute(this.valueColumn.getName(), "Ja");
//		} else {
//			this.infoBoxRecord.setAttribute(this.valueColumn.getName(), "Nein");
//		}
//		this.presenter.articleNeedsEditsChanged().addHandler(new Handler<EventArgs>() {
//
//			@Override
//			public void invoke(Object sender, EventArgs e) {
//				if(presenter.articleNeedsEdit()) {
//					infoBoxRecord.setAttribute(valueColumn.getName(), "Ja");
//				} else {
//					infoBoxRecord.setAttribute(valueColumn.getName(), "Nein");
//				}
//				generalInfoGrid.refreshFields();
//			}
//		});
	}

	private void bindArticleLengthRecord() {
		this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				articleLengthRecord.setAttribute(valueColumn.getName(), presenter.getArticleInfo().getBytes());
				generalInfoGrid.refreshFields();
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

	@Override
	public String getName() {
		return "Article";
	}
}
