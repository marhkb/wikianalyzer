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
	        categoryCoincidentRecord, restrictionInfoRecord, cooperationPossibilityRecord, userclassCommitsRecord, userclassCommitsPerDayRecord,
			userclassRevertRecord, userclassCommentsPerCommitRecord, userclassUserDiscussionRecord,
			userclassSelfDiscussionRecord, similarityRecord;
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
		this.reputationInfoRecord = new ListGridRecord();
		this.reputationInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Reputation:");
		this.restrictionInfoRecord = new ListGridRecord();
		this.restrictionInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Gesperrt:");
		this.userclassCommitsRecord = new ListGridRecord();
		this.userclassCommitsRecord.setAttribute(this.userComparisonAttribute.getName(), "Einordnung (Commits ingesamt):");
		this.userclassCommitsPerDayRecord = new ListGridRecord();
		this.userclassCommitsPerDayRecord.setAttribute(this.userComparisonAttribute.getName(), "Einordnung (Commits/Tag):");
		this.userclassRevertRecord = new ListGridRecord();
		this.userclassRevertRecord.setAttribute(this.userComparisonAttribute.getName(), "Einordnung (Reverts/Commits):");
		this.userclassCommentsPerCommitRecord = new ListGridRecord();
		this.userclassCommentsPerCommitRecord.setAttribute(this.userComparisonAttribute.getName(),
														   "Einordnung (Kommentare/Commits):");
		this.userclassUserDiscussionRecord = new ListGridRecord();
		this.userclassUserDiscussionRecord.setAttribute(this.userComparisonAttribute.getName(),
														"Diskussionen über andere Nutzer:");
		this.userclassSelfDiscussionRecord = new ListGridRecord();
		this.userclassSelfDiscussionRecord.setAttribute(this.userComparisonAttribute.getName(),
														"Diskussionen über sich selbst:");
		this.categoryAmountInfoRecord = new ListGridRecord();
		this.categoryAmountInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "An Kategorien mit gewirkt:");
		this.categoryCoincidentRecord = new ListGridRecord();
		this.categoryCoincidentRecord.setAttribute(this.userComparisonAttribute.getName(), "Deckungsgleichheit d. Kategorien:");
		this.restrictionInfoRecord = new ListGridRecord();
		this.restrictionInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Strafen:");
		this.cooperationPossibilityRecord = new ListGridRecord();
		this.cooperationPossibilityRecord.setAttribute(this.userComparisonAttribute.getName(), "Kooperationsfähigkeit (bzgl. Kategorien):");
		this.similarityRecord = new ListGridRecord();
		this.similarityRecord.setAttribute(this.userComparisonAttribute.getName(), "Ähnlichkeit:");
		
		this.userComparisonGrid.setShowRecordComponents(true);
		this.userComparisonGrid.setShowRecordComponentsByCell(true);
		this.userComparisonGrid.setShowAllRecords(true);
		this.userComparisonGrid.setCanResizeFields(false);
		this.userComparisonGrid.setShowHeaderMenuButton(false);
		
		this.userComparisonGrid.setFields(this.userComparisonAttribute, this.userOneValue, this.userTwoValue);
		this.userComparisonGrid.addData(this.userLinkInfoRecord);
		this.userComparisonGrid.addData(this.signUpInfoRecord);
		this.userComparisonGrid.addData(this.commitsInfoRecord);
		this.userComparisonGrid.addData(this.reputationInfoRecord);
		this.userComparisonGrid.addData(this.categoryAmountInfoRecord);
		this.userComparisonGrid.addData(this.restrictionInfoRecord);
		this.userComparisonGrid.addData(this.userclassCommitsRecord);
		this.userComparisonGrid.addData(this.userclassCommitsPerDayRecord);
		this.userComparisonGrid.addData(this.userclassRevertRecord);
		this.userComparisonGrid.addData(this.userclassCommentsPerCommitRecord);
		this.userComparisonGrid.addData(this.userclassUserDiscussionRecord);
		this.userComparisonGrid.addData(this.userclassSelfDiscussionRecord);
		this.userComparisonGrid.addData(this.cooperationPossibilityRecord);
		this.userComparisonGrid.addData(this.similarityRecord);
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
		this.bindReputationInfoRecord();
		this.bindCategoryAmountInfoRecord();
		this.bindRestrictionInfoRecord();
		//this.bindCooperationPossibility();
		this.bindUserclassCommitsRecord();
		this.bindUserclassSelfDiscussionRecord();
		this.bindUserclassCommitsPerDayRecord();
		this.bindUserclassRevertRecord();
		this.bindUserclassCommentsPerCommitRecord();
		this.bindUserclassUserDiscussionRecord();
		//this.bindSimilarityRecordRecord();
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
	
	private void bindReputationInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				reputationInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getSignInDate());
				reputationInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getSignInDate());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindCategoryAmountInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				categoryAmountInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getSignInDate());
				categoryAmountInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getSignInDate());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindRestrictionInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				restrictionInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getSignInDate());
				restrictionInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getSignInDate());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindCooperationPossibility() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				cooperationPossibilityRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getSignInDate());
				cooperationPossibilityRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getSignInDate());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassCommitsRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassCommitsRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassNumOfCommits());
				userclassCommitsRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassNumOfCommits());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassCommitsPerDayRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassCommitsPerDayRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassAvgCommits());
				userclassCommitsPerDayRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassAvgCommits());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassRevertRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassRevertRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassReverts());
				userclassRevertRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassReverts());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassCommentsPerCommitRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassCommentsPerCommitRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassCleaness());
				userclassCommentsPerCommitRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassCleaness());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassUserDiscussionRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassUserDiscussionRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassUserDiscussion());
				userclassUserDiscussionRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassUserDiscussion());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassSelfDiscussionRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userclassSelfDiscussionRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassSelfDiscussion());
				userclassSelfDiscussionRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassSelfDiscussion());
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindSimilarityRecordRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				similarityRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getUserclassSelfDiscussion());
				similarityRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getUserclassSelfDiscussion());
				userComparisonGrid.refreshFields();
			}
		});
	}
}
