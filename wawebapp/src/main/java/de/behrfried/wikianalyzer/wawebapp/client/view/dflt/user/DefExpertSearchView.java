package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.inject.Inject;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;

public class DefExpertSearchView extends ExpertSearchView {
	private Label filterLabel, gridLabel;
	private HLayout expertViewContainer;
	private VLayout filterContainer, gridContainer;
	private DataSource filterData;
	private final FilterBuilder filter;
	private Button searchButton;
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
		this.filter = new FilterBuilder();
		this.init();
	}

	public void init() {
		this.filterLabel = new Label("Geben sie hier ihre Suchkriterien ein:");
		this.filterLabel.setHeight(20);
		this.searchButton = new Button("Nutzer suchen");
		this.filterContainer = new VLayout();
		this.filterContainer.addMember(this.filterLabel);
		//this.filterContainer.addMember(this.filter);
		this.filterContainer.addMember(this.searchButton);
		this.filterContainer.setWidth("40%");

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
		this.expertViewContainer.addMember(this.filterContainer);
		this.expertViewContainer.addMember(this.gridContainer);
		this.expertViewContainer.setWidth100();
		this.expertViewContainer.setHeight100();
		
		this.addChild(this.expertViewContainer);
	}
}
