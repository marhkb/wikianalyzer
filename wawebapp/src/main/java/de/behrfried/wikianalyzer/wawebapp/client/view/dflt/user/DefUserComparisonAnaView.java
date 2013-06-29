package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonView.Presenter;

public class DefUserComparisonAnaView extends UserComparisonAnaView {

	private Label userComparisonChartLabel, comparisonGridLabel;
	private HLayout userComparisonAnaContainer;
	private VLayout userComparisonChartContainer, userComparisonGridContainer;
	private HTMLPanel userComparisonChart;
	private HTMLFlow user1Link, user2Link;
	private ListGridRecord userLinkInfoRecord, signUpInfoRecord, commitsInfoRecord, reputationInfoRecord, categoryAmountInfoRecord,
	        categoryCoincidentRecord, restrictionInfoRecord, commitSizeInfoRecord, cooperationPossibility;
	private final ListGrid userComparisonGrid = new ListGrid() {

		@Override
		protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
			final String tmpString = record.getAttribute("copmAttr");
			if(this.getFieldName(colNum).equals("userOneV")) {
				if(tmpString.equals("Nutzername:")) {
					DefUserComparisonAnaView.this.user1Link = new HTMLFlow();
					// DefUserComparisonAnaView.this.bindUserLinkRecord();
					return DefUserComparisonAnaView.this.user1Link;
				}
			} else if(this.getFieldName(colNum).equals("userTwoV")) {
				if(tmpString.equals("Nutzername:")) {
					DefUserComparisonAnaView.this.user2Link = new HTMLFlow();
					// DefUserComparisonAnaView.this.bindUserLinkRecord();
					return DefUserComparisonAnaView.this.user2Link;
				}
			}
			return null;
		}
	};
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
		this.bind();
	}

	private void init() {
		this.comparisonGridLabel = new Label("Vergleich der Nutzer");
		this.comparisonGridLabel.setHeight(20);
		this.userComparisonAttribute = new ListGridField("copmAttr", "Vergleichskriterium");
		this.userOneValue = new ListGridField("userOneV", "Wert Nutzer 1");
		this.userTwoValue = new ListGridField("userTwoV", "Wert Nutzer 2");
		this.userLinkInfoRecord = new ListGridRecord();
		this.userLinkInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Nutzername:");
		this.signUpInfoRecord = new ListGridRecord();
		this.signUpInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Mitglied seit:");
		this.commitsInfoRecord = new ListGridRecord();
		this.commitsInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Commits (pro Tag):");
		this.commitSizeInfoRecord = new ListGridRecord();
		this.commitSizeInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Commitgröße (in Bytes):");
		this.reputationInfoRecord = new ListGridRecord();
		this.reputationInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Reputation:");
		this.categoryAmountInfoRecord = new ListGridRecord();
		this.categoryAmountInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Kategorien:");
		this.categoryCoincidentRecord = new ListGridRecord();
		this.categoryCoincidentRecord.setAttribute(this.userComparisonAttribute.getName(), "Deckungsgleichheit d. Kategorien:");
		this.restrictionInfoRecord = new ListGridRecord();
		this.restrictionInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Strafen:");
		this.cooperationPossibility = new ListGridRecord();
		this.cooperationPossibility.setAttribute(this.userComparisonAttribute.getName(), "Kooperationsfähigkeit:");
		
		this.userComparisonGrid.setShowRecordComponents(true);
		this.userComparisonGrid.setShowRecordComponentsByCell(true);
		this.userComparisonGrid.setShowAllRecords(true);
		this.userComparisonGrid.setCanResizeFields(false);
		this.userComparisonGrid.setShowHeaderMenuButton(false);
		
		this.userComparisonGrid.setFields(this.userComparisonAttribute, this.userOneValue, this.userTwoValue);
		this.userComparisonGrid.addData(this.userLinkInfoRecord);
		this.userComparisonGrid.addData(this.signUpInfoRecord);
		this.userComparisonGrid.addData(this.commitsInfoRecord);
		this.userComparisonGrid.addData(this.commitSizeInfoRecord);
		this.userComparisonGrid.addData(this.reputationInfoRecord);
		this.userComparisonGrid.addData(this.categoryAmountInfoRecord);
		this.userComparisonGrid.addData(this.categoryCoincidentRecord);
		this.userComparisonGrid.addData(this.restrictionInfoRecord);
		this.userComparisonGrid.addData(this.cooperationPossibility);
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
		// this.userComparisonChartContainer.addMember(this.userComparisonChart);

		this.userComparisonAnaContainer = new HLayout();
		this.userComparisonAnaContainer.addMembers(this.userComparisonGridContainer, this.userComparisonChartContainer);
		this.userComparisonAnaContainer.setWidth100();
		this.userComparisonAnaContainer.setHeight100();
		this.addChild(this.userComparisonAnaContainer);
	}

	private void bind() {
		this.bindUserLinksRecord();
		this.bindSignUpInfosRecord();
		this.bindUsersCommitsRecord();
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
	
	private void bindUserLinksRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {
			@Override
			public void invoke(Object sender, EventArgs e) {
				user1Link.setContents("<a href='http://de.wikipedia.org/wiki/Benutzer:" + presenter.getUserComparisonInfo().getUserInfo1().getUserName()
				        + "' target='_blank'>" + presenter.getUserComparisonInfo().getUserInfo1().getUserName() + "</a>");
				user2Link.setContents("<a href='http://de.wikipedia.org/wiki/Benutzer:" + presenter.getUserComparisonInfo().getUserInfo2().getUserName()
				        + "' target='_blank'>" + presenter.getUserComparisonInfo().getUserInfo2().getUserName() + "</a>");
				userComparisonGrid.refreshFields();
			}
		});
	}

	private void bindSignUpInfosRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				signUpInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getSignInDate());
				signUpInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getSignInDate());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUsersCommitsRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				commitsInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getCommits()+" ("+presenter.getUserComparisonInfo().getUserInfo1().getCommitsPerDay()+")");
				commitsInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getCommits()+" ("+presenter.getUserComparisonInfo().getUserInfo2().getCommitsPerDay()+")");
				userComparisonGrid.refreshFields();
			}
		});
	}
}
