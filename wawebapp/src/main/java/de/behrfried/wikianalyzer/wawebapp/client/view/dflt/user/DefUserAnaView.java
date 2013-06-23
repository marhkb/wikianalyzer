package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserView.Presenter;

public class DefUserAnaView extends UserAnaView {

	private final Presenter presenter;

	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	private Label userArticleLabel, userEditTypeLabel;
	private HTMLPanel userArticleChartContainer, userEditTypeChartContainer;
	private VLayout userAnaContainer;
	private HLayout userArticleAnaContainer, userEditTypeContainer;
	private ListGrid userArticleGrid, userEditTypeGrid;
	private ListGridField articleArticleColumn, articleCategoryColumn, articleCommitsColumn, articleQuantityColumn, editTypeEditTypeColumn,
	        editTypeCommitsColumn, editTypeQuantityColumn;;

	/**
	 * Creates an instance of {@link DefUserView}. All arguments are injected by
	 * Gin.
	 * 
	 * @param parentView
	 */
	@Inject
	public DefUserAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
	}

	private void init() {
		this.userArticleLabel = new Label("Vom Benutzer bearbeitete Artikel");
		this.userArticleChartContainer = new HTMLPanel("");
		this.userArticleChartContainer.setWidth("50%");
		this.createArticleChart();
		this.articleArticleColumn = new ListGridField("articleColumn", "Artikel");
		this.articleCategoryColumn = new ListGridField("categoryColumn", "Kategorie");
		this.articleCommitsColumn = new ListGridField("commitsColumn", "Commits");
		this.articleQuantityColumn = new ListGridField("quantityColumn", "Quantität");
		this.userArticleGrid = new ListGrid();
		this.userArticleGrid.setWidth("50%");
		this.userArticleGrid.setFields(this.articleArticleColumn, this.articleCategoryColumn, this.articleCommitsColumn, this.articleQuantityColumn);
		this.userArticleAnaContainer = new HLayout();
		this.userArticleAnaContainer.addMember(this.userArticleChartContainer);
		this.userArticleAnaContainer.addMember(this.userArticleGrid);

		this.userEditTypeLabel = new Label();
		this.userEditTypeChartContainer = new HTMLPanel("");
		this.createEditTypeChart();
		this.editTypeEditTypeColumn = new ListGridField("typeColumn", "Art der Änderung");
		this.editTypeCommitsColumn = new ListGridField("commitsColumn", "Commits");
		this.editTypeQuantityColumn = new ListGridField("quantityColumn", "Quantität");
		this.userEditTypeGrid = new ListGrid();
		this.userEditTypeGrid.setFields(this.editTypeEditTypeColumn, this.editTypeCommitsColumn, this.editTypeQuantityColumn);
		this.userEditTypeContainer = new HLayout();
		this.userEditTypeContainer.addMember(this.userEditTypeChartContainer);
		this.userEditTypeContainer.addMember(this.userEditTypeGrid);

		this.userAnaContainer = new VLayout();
		this.userAnaContainer.addMembers(this.userArticleLabel, this.userArticleAnaContainer, this.userEditTypeContainer);
		this.addChild(this.userAnaContainer);
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

	private void createEditTypeChart() {
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
				userEditTypeChartContainer.add(chart);
			}
		};
		VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
	}
}
