package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import java.util.ArrayList;
import java.util.List;

public class DefExpertSearchView extends ExpertSearchView {

	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;
	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;
	private final List<HLayout> latest = new ArrayList<HLayout>();
	private Label filterLabel, gridLabel;
	private HLayout expertViewContainer;
	private VLayout expertSearchContainer, gridContainer;
	private Button searchButton, addRowButton;
	private Button deleteRowButton;
	private ListGrid expertsGrid = new ListGrid() {

		@Override
		protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
			if(this.getFieldName(colNum).equals("userNameRecord")) {
				Window.alert(record.getAttribute(userNameRecord.getName()));
				final HTMLFlow htmlFlow = new HTMLFlow();
				htmlFlow.setContents("<a href='http://de.wikipedia.org/wiki/Benutzer:" + record.getAttribute(userNameRecord.getName())
				        + "' target='_blank'>" + record.getAttribute(userNameRecord.getName()) + "</a>");
				record.setAttribute(userNameRecord.getName(), "");
				return htmlFlow;
				// if(tmpString.equals("Link zur gesuchten Seite:")) {
				// }
			}
			return null;
		}
	};
	private ListGridField userNameRecord, searchResultConformityRecord;
	private int i = 0;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments are
	 * injected by Gin
	 * 
	 * @throws IllegalArgumentException
	 */
	@Inject
	public DefExpertSearchView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
		this.bind();
	}

	private void addNewSearchCriteriaRow() {
		final ComboBoxItem searchBox;
		final DynamicForm searchBoxContainer, searchCategoryContainer;
		final RadioGroupItem searchCategory;
		final HLayout searchCriteriaContainer;

		searchCriteriaContainer = new HLayout();
		searchCriteriaContainer.setHeight(30);
		searchCriteriaContainer.setBorder("1px blue");

		searchBox = new ComboBoxItem();
		searchBox.setWidth(200);
		searchBox.setShowTitle(false);
		searchBox.setShowPickerIcon(true);
		searchBoxContainer = new DynamicForm();
		searchBoxContainer.setAlign(Alignment.CENTER);
		searchBoxContainer.setBackgroundColor("white");
		searchBoxContainer.setItems(searchBox);
		searchCriteriaContainer.addMember(searchBoxContainer);

		searchCategory = new RadioGroupItem();
		searchCategory.setValueMap("Artikel", "Kategorie");
		searchCategory.setShowTitle(false);
		searchCategory.setDefaultValue("Artikel");
		searchCategoryContainer = new DynamicForm();
		searchCategoryContainer.setBackgroundColor("white");
		searchCategoryContainer.setItems(searchCategory);
		searchCriteriaContainer.addMember(searchCategoryContainer);
		this.expertSearchContainer.addMember(searchCriteriaContainer, this.expertSearchContainer.getMemberNumber(this.addRowButton));
		if(!this.latest.isEmpty()) {
			this.latest.get(this.latest.size() - 1).removeChild(this.deleteRowButton);
		}
		deleteRowButton = new Button("Kriterium entfernen");
		deleteRowButton.setWidth(130);
		searchCriteriaContainer.addMember(deleteRowButton);
		final Button delRButton = this.deleteRowButton;
		deleteRowButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				presenter.getRemoveTitleOrCategoryCommand().execute(null);
				latest.get(latest.size() - 1).removeChild(delRButton);
				expertSearchContainer.removeChild(latest.get(latest.size() - 1));
				latest.remove(latest.size() - 1);
				if(!latest.isEmpty()) {
					latest.get(latest.size() - 1).addMember(delRButton);
				}

			}
		});

		latest.add(searchCriteriaContainer);

		this.expertSearchContainer.addMember(this.addRowButton);
		this.bindCriterionField(searchBox, this.latest.size());
		this.bindSearchCategory(searchCategory, this.latest.size());
	}

	private void addFirstSearchCriteriaRow() {
		final ComboBoxItem searchBox;
		final DynamicForm searchBoxContainer, searchCategoryContainer;
		final RadioGroupItem searchCategory;
		final HLayout searchCriteriaContainer;

		searchCriteriaContainer = new HLayout();
		searchCriteriaContainer.setHeight(30);
		searchCriteriaContainer.setBorder("1px blue");

		searchBox = new ComboBoxItem();
		searchBox.setWidth(200);
		searchBox.setShowTitle(false);
		searchBox.setShowPickerIcon(true);
		searchBoxContainer = new DynamicForm();
		searchBoxContainer.setAlign(Alignment.CENTER);
		searchBoxContainer.setBackgroundColor("white");
		searchBoxContainer.setItems(searchBox);
		searchCriteriaContainer.addMember(searchBoxContainer);

		searchCategory = new RadioGroupItem();
		searchCategory.setValueMap("Artikel", "Kategorie");
		searchCategory.setShowTitle(false);
		searchCategory.setDefaultValue("Artikel");
		searchCategoryContainer = new DynamicForm();
		searchCategoryContainer.setBackgroundColor("white");
		searchCategoryContainer.setItems(searchCategory);
		searchCriteriaContainer.addMember(searchCategoryContainer);
		this.expertSearchContainer.addMember(searchCriteriaContainer);
		this.expertSearchContainer.addMember(this.addRowButton);
		this.bindCriterionField(searchBox, 0);
		this.bindSearchCategory(searchCategory, 0);
	}

	public void init() {
		this.filterLabel = new Label("Geben sie hier ihre Suchkriterien ein:");
		this.filterLabel.setHeight(20);
		this.addRowButton = new Button("Suchkriterium hinzufügen");
		this.addRowButton.setWidth(150);
		this.searchButton = new Button("Nutzer suchen");
		this.searchButton.setPadding(20);
		this.searchButton.setWidth(150);
		this.searchButton.setAlign(Alignment.CENTER);
		this.expertSearchContainer = new VLayout();
		this.expertSearchContainer.setOverflow(Overflow.AUTO);
		this.expertSearchContainer.addMember(this.filterLabel);

		this.addFirstSearchCriteriaRow();

		this.expertSearchContainer.addMembers(this.addRowButton, this.searchButton);
		this.expertSearchContainer.setWidth("40%");

		this.gridLabel = new Label("Gefundene Ergebnisse: ");
		this.gridLabel.setHeight(20);
		this.userNameRecord = new ListGridField("userNameRecord", "Nutzername");
		this.searchResultConformityRecord = new ListGridField("resultConformity", "Kriterienübereinstimmung");

		this.expertsGrid.setShowRecordComponents(true);
		this.expertsGrid.setShowRecordComponentsByCell(true);
		this.expertsGrid.setShowAllRecords(true);
		this.expertsGrid.setCanResizeFields(false);
		this.expertsGrid.setShowHeaderMenuButton(false);
		this.expertsGrid.setFields(this.userNameRecord, this.searchResultConformityRecord);
		this.gridContainer = new VLayout();
		this.gridContainer.addMember(this.gridLabel);
		this.gridContainer.addMember(this.expertsGrid);
		this.gridContainer.setWidth("60%");

		this.expertViewContainer = new HLayout();
		this.expertViewContainer.addMember(this.expertSearchContainer);
		this.expertViewContainer.addMember(this.gridContainer);
		this.expertViewContainer.setWidth100();
		this.expertViewContainer.setHeight100();

		this.addChild(this.expertViewContainer);
	}

	private void bind() {
		this.bindSearchButton();
		this.bindAddRowButton();
		this.bindExpertsGrid();
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null) || !presenter.getSearchStatus());
			}
		});
		this.presenter.searchStatusChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs eventArgs) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null) || !presenter.getSearchStatus());
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				presenter.getSendCommand().execute(null);
			}
		});
	}

	private void bindAddRowButton() {
		addRowButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				presenter.getAddTitleOrCategoryCommand().execute(null);
				addNewSearchCriteriaRow();
			}
		});
	}

	private void bindExpertsGrid() {
		presenter.criterionInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs eventArgs) {
				while(expertsGrid.getRecordList().getLength() > 0) {
					expertsGrid.removeData(expertsGrid.getRecord(0));
				}
				for(final CriterionInfo.User u : presenter.getCriterionInfo().getUsers()) {
					final ListGridRecord lgr = new ListGridRecord();
					lgr.setAttribute(userNameRecord.getName(), u.getUserName());
					lgr.setAttribute(searchResultConformityRecord.getName(), u.getMatch());
					expertsGrid.addData(lgr);
				}
			}
		});
	}

	private void bindCriterionField(final ComboBoxItem searchBox, final int i) {
		searchBox.setValue(this.presenter.getTitleOrCategories().get(i).getTitle());
		searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				presenter.getTitleOrCategories().get(i).setName(searchBox.getValueAsString());
				presenter.raiseSuggestionsChanged(i);
			}
		});
		this.presenter.titleOrCategoriesChanged().addHandler(new Handler<IntegerChangedEventArgs>() {

			public void invoke(final Object sender, final IntegerChangedEventArgs e) {
				if(e.getIndex() == i) {
					if(!searchBox.equals(presenter.getTitleOrCategories().get(i).getTitle())) {
						searchBox.setValue(presenter.getTitleOrCategories().get(i).getTitle());
					}
				}
			}
		});

		searchBox.setValueMap(this.presenter.getArticleSuggestions().get(i));
		this.presenter.articleSuggestionsChanged().addHandler(new Handler<IntegerChangedEventArgs>() {

			public void invoke(final Object sender, final IntegerChangedEventArgs e) {
				if(e.getIndex() == i) {
					searchBox.setValueMap(presenter.getArticleSuggestions().get(i));
					searchBox.showPicker();
				}
			}
		});
	}

	private void bindSearchCategory(final RadioGroupItem radioGroupItem, final int i) {
		radioGroupItem.setValue(this.presenter.getTitleOrCategories().get(i).isCategory() ? "Kategorie" : "Artikel");
		radioGroupItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent changedEvent) {
				presenter.getTitleOrCategories().get(i).setCategory(radioGroupItem.getValueAsString().equals("Kategorie"));
				presenter.getTitleOrCategories().get(i).setName("");
				presenter.raiseTitleOrCategoryChanged(i);
			}
		});
	}
}
