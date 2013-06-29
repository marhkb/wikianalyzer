package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.inject.Inject;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;

public class DefExpertSearchView extends ExpertSearchView {

	private Label filterLabel, gridLabel;
	private HLayout expertViewContainer;
	private VLayout expertSearchContainer, gridContainer;
	private Button searchButton, addRowButton;
	private ListGrid serachResult;
	private ListGridField userName, searchResultConformity;

	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;
	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments are
	 * injected by Gin
	 * 
	 * @param parentView
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

	public void addNewSearchCriteriaRow() {
		final ComboBoxItem searchBox;
		final DynamicForm searchBoxContainer, searchCategoryContainer;
		final Button deleteRowButton;
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
		if(this.expertSearchContainer.getChildren().length > 4) {
			deleteRowButton = new Button("Kriterium entfernen");
			deleteRowButton.setWidth(130);
			searchCriteriaContainer.addMember(deleteRowButton);
			deleteRowButton.addClickHandler(new ClickHandler() {

				public void onClick(final ClickEvent event) {
					System.out.println(deleteRowButton.getParent());
				}
			});
		}
		this.expertSearchContainer.addMember(this.addRowButton);
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
		this.expertSearchContainer.addMembers(this.filterLabel, this.addRowButton, this.searchButton);
		this.addNewSearchCriteriaRow();
		this.expertSearchContainer.setWidth("40%");

		this.gridLabel = new Label("Gefundene Ergebnisse: ");
		this.gridLabel.setHeight(20);
		this.userName = new ListGridField("userName", "Nutzername");
		this.searchResultConformity = new ListGridField("resultConformity", "Kriterienübereinstimmung");
		this.serachResult = new ListGrid();
		this.serachResult.setFields(this.userName, this.searchResultConformity);
		this.gridContainer = new VLayout();
		this.gridContainer.addMember(this.gridLabel);
		this.gridContainer.addMember(this.serachResult);
		this.gridContainer.setWidth("60%");

		this.expertViewContainer = new HLayout();
		this.expertViewContainer.addMember(this.expertSearchContainer);
		this.expertViewContainer.addMember(this.gridContainer);
		this.expertViewContainer.setWidth100();
		this.expertViewContainer.setHeight100();

		this.addChild(this.expertViewContainer);
	}
	
	private void bind() {
		this.bindAddRowButton();
	}
	
	private void bindAddRowButton() {
		addRowButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				addNewSearchCriteriaRow();
			}
		});
	}
}
