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
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonView.Presenter;

public class DefUserComparisonAnaView extends UserComparisonAnaView {

	private Label userComparisonChartLabel, comparisonGridLabel;
	private HLayout userComparisonAnaContainer;
	private VLayout userComparisonChartContainer, userComparisonGridContainer;
	private HTMLPanel userComparisonChart;
	private ListGrid userComparisonGrid;
	private ListGridField userComparisonAttribute, userOneValue, userTwoValue;
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
	public DefUserComparisonAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
	}

	private void init() {
		this.comparisonGridLabel = new Label("Vergleich der Nutzer");
		this.comparisonGridLabel.setHeight(20);
		this.userComparisonGrid = new ListGrid();
		this.userComparisonAttribute = new ListGridField("copmAttr", "Vergleichskriterium");
		this.userOneValue = new ListGridField("userOneV", "Wert Nutzer 1");
		this.userTwoValue = new ListGridField("userTwoV", "Wert Nutzer 2");
		this.userComparisonGrid.setFields(this.userComparisonAttribute, this.userOneValue, this.userTwoValue);
		this.userComparisonGridContainer = new VLayout();
		this.userComparisonGridContainer.setWidth("50%");
		this.userComparisonGridContainer.setHeight100();
		this.userComparisonGridContainer.addMembers(this.comparisonGridLabel, this.userComparisonGrid);

		this.userComparisonChartLabel = new Label("Grafischer Vergleich");
		this.userComparisonChartLabel.setHeight(20);
		this.userComparisonChart = new HTMLPanel("");
		this.createUserComparisonChart();
		this.userComparisonChartContainer = new VLayout();
		this.userComparisonChartContainer.setWidth("30%");
		this.userComparisonChartContainer.setHeight100();
		this.userComparisonChartContainer.addMember(this.userComparisonChartLabel);
		//this.userComparisonChartContainer.addMember(this.userComparisonChart);
		
		this.userComparisonAnaContainer = new HLayout();
		this.userComparisonAnaContainer.addMembers(this.userComparisonGridContainer, this.userComparisonChartContainer);
		this.userComparisonAnaContainer.setWidth100();
		this.userComparisonAnaContainer.setHeight100();
		this.addChild(this.userComparisonAnaContainer);
	}
	
	private void createUserComparisonChart() {
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
				userComparisonChart.add(chart);
			}
		};
		VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
	}
}
