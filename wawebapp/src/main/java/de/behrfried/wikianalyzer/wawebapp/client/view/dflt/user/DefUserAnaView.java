package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;

public class DefUserAnaView extends UserAnaView {

	private final Presenter presenter;

	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	private Label userArticleLabel;
	private HTMLPanel userArticleChartContainer;
	private VLayout userAnaContainer;
	private HLayout userArticleAnaContainer;
	private ListGrid userArticleGrid;
	private ListGridField articleArticleColumn, articleCategoryColumn, articleCommitsColumn, articleQuantityColumn;

	/**
	 * Creates an instance of {@link DefUserView}. All arguments are injected by
	 * Gin.
	 */
	@Inject
	public DefUserAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
		this.bind();
	}

	private void init() {
		this.userArticleLabel = new Label("Vom Benutzer bearbeitete Artikel");
		this.userArticleLabel.setHeight("15px");
		this.userArticleLabel.setAlign(Alignment.CENTER);
		this.userArticleChartContainer = new HTMLPanel("");
		this.userArticleChartContainer.setWidth("50%");
		this.createArticleChart();
		this.articleArticleColumn = new ListGridField("articleColumn", "Artikel");
		this.articleCategoryColumn = new ListGridField("categoryColumn", "Kategorie");
		this.articleCommitsColumn = new ListGridField("commitsColumn", "Commits");
		this.articleQuantityColumn = new ListGridField("quantityColumn", "Differenz (Bytes)");
		this.userArticleGrid = new ListGrid();
		this.userArticleGrid.setWidth("50%");
		this.userArticleGrid.setFields(this.articleArticleColumn, this.articleCategoryColumn, this.articleCommitsColumn, this.articleQuantityColumn);
		this.userArticleAnaContainer = new HLayout();
		// this.userArticleAnaContainer.addMember(this.userArticleChartContainer);
		this.userArticleAnaContainer.addMember(this.userArticleGrid);
		this.userArticleAnaContainer.setHeight("50%");

		this.userAnaContainer = new VLayout();
		this.userAnaContainer.addMembers(this.userArticleLabel, this.userArticleAnaContainer);
		this.userAnaContainer.setMargin(10);
		this.userAnaContainer.setHeight100();
		this.userAnaContainer.setWidth100();
		this.addChild(this.userAnaContainer);
	}
	
	private void bind() {
		this.bindUserArticleGrid();
	}

	private void createArticleChart() {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				DataTable data = DataTable.create();
				data.addColumn(AbstractDataTable.ColumnType.STRING, "Task");
				data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Hours per Day");
				data.addRows(2);
				data.setValue(0, 0, "Work");
				data.setValue(0, 1, 14);
				data.setValue(1, 0, "Sleep");
				data.setValue(1, 1, 10);
				ColumnChart.Options options = ColumnChart.Options.create();
				options.setWidth(500);
				options.setHeight(150);
				options.set3D(true);
				options.setTitle("My Daily Activities");
				ColumnChart chart = new ColumnChart(data, options);
				userArticleChartContainer.add(chart);
			}
		};
		VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
	}
	
	private void bindUserArticleGrid() {
		this.presenter.userInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs eventArgs) {
				while(userArticleGrid.getRecordList().getLength() > 0) {
					userArticleGrid.removeData(userArticleGrid.getRecord(0));
				}
				for(final UserInfo.ArticleEdited catEd : presenter.getUserInfo().getEditedCategories()) {
					final ListGridRecord lgr = new ListGridRecord();
					lgr.setAttribute(articleArticleColumn.getName(), catEd.getArticle());
					lgr.setAttribute(articleCategoryColumn.getName(), catEd.getCategory());
					lgr.setAttribute(articleCommitsColumn.getName(), catEd.getNumOfCommits());
					lgr.setAttribute(articleQuantityColumn.getName(), catEd.getSizediff());
					userArticleGrid.addData(lgr);
				}
			}
		});
	}
	
	

	}
