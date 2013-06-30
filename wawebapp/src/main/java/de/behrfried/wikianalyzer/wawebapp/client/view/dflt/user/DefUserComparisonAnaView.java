package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

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

	private Label comparisonGridLabel;
	private HLayout userComparisonAnaContainer;
	private VLayout userComparisonGridContainer;
	private HTMLFlow user1Link, user2Link;
	private ListGridRecord userLinkInfoRecord, signUpInfoRecord, commitsInfoRecord, reputationInfoRecord, isBotRecord,
			categoryAmountInfoRecord,
	        restrictionInfoRecord, commitsPerDayRecord, articleAmountInfoRecord, 
			revertCommitsRecord, commentsPerCommitRecord, userDiscussionRecord,
			selfDiscussionRecord, similarityRecord, similarArticlesRecord, similarCategoryRecord, abusesRecord;
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
	private ListGridField userComparisonAttribute, userOneValue, userTwoValue, congruarityValue;
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
		this.congruarityValue = new ListGridField("congu","Übereinstimmung");
		this.userLinkInfoRecord = new ListGridRecord();
		this.userLinkInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Nutzername:");
		this.signUpInfoRecord = new ListGridRecord();
		this.signUpInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Mitglied seit:");
		this.commitsInfoRecord = new ListGridRecord();
		this.commitsInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Commits:");
		this.reputationInfoRecord = new ListGridRecord();
		this.reputationInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Reputation:");
		this.isBotRecord = new ListGridRecord();
		this.isBotRecord.setAttribute(this.userComparisonAttribute.getName(), "Bot:");
		this.restrictionInfoRecord = new ListGridRecord();
		this.restrictionInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "Gesperrt:");
		this.abusesRecord = new ListGridRecord();
		this.abusesRecord.setAttribute(this.userComparisonAttribute.getName(), "Vergehen:");
		this.commitsPerDayRecord = new ListGridRecord();
		this.commitsPerDayRecord.setAttribute(this.userComparisonAttribute.getName(), "Commits/Tag:");
		this.revertCommitsRecord = new ListGridRecord();
		this.revertCommitsRecord.setAttribute(this.userComparisonAttribute.getName(), "Verhältnis Reverts/Commits:");
		this.commentsPerCommitRecord = new ListGridRecord();
		this.commentsPerCommitRecord.setAttribute(this.userComparisonAttribute.getName(),
														   "Verhältnis Kommentare/Commits:");
		this.userDiscussionRecord = new ListGridRecord();
		this.userDiscussionRecord.setAttribute(this.userComparisonAttribute.getName(),
														"Diskussionen über andere Nutzer:");
		this.selfDiscussionRecord = new ListGridRecord();
		this.selfDiscussionRecord.setAttribute(this.userComparisonAttribute.getName(),
														"Diskussionen über sich selbst:");
		this.categoryAmountInfoRecord = new ListGridRecord();
		this.categoryAmountInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "An Kategorien mit gewirkt (Koop):");
		this.articleAmountInfoRecord = new ListGridRecord();
		this.articleAmountInfoRecord.setAttribute(this.userComparisonAttribute.getName(), "An Artikeln mit gewirkt (Koop):");
		this.similarityRecord = new ListGridRecord();
		this.similarityRecord.setAttribute(this.userComparisonAttribute.getName(), "Ähnlichkeit gesamt:");
		this.similarArticlesRecord = new ListGridRecord();
		this.similarArticlesRecord.setAttribute(this.userComparisonAttribute.getName(), "Selbe Artikel:");
		this.similarCategoryRecord = new ListGridRecord();
		this.similarCategoryRecord.setAttribute(this.userComparisonAttribute.getName(), "Selbe Kategorien:");
		
		this.userComparisonGrid.setShowRecordComponents(true);
		this.userComparisonGrid.setShowRecordComponentsByCell(true);
		this.userComparisonGrid.setShowAllRecords(true);
		this.userComparisonGrid.setCanResizeFields(false);
		this.userComparisonGrid.setShowHeaderMenuButton(false);
		
		this.userComparisonGrid.setFields(this.userComparisonAttribute, this.userOneValue, this.userTwoValue, this.congruarityValue);
		this.userComparisonGrid.addData(this.userLinkInfoRecord);
		this.userComparisonGrid.addData(this.signUpInfoRecord);
		this.userComparisonGrid.addData(this.commitsInfoRecord);
		this.userComparisonGrid.addData(this.commitsPerDayRecord);
		this.userComparisonGrid.addData(this.reputationInfoRecord);
		this.userComparisonGrid.addData(this.isBotRecord);
		this.userComparisonGrid.addData(this.categoryAmountInfoRecord);
		this.userComparisonGrid.addData(this.similarCategoryRecord);
		this.userComparisonGrid.addData(this.articleAmountInfoRecord);
		this.userComparisonGrid.addData(this.similarArticlesRecord);
		this.userComparisonGrid.addData(this.abusesRecord);
		this.userComparisonGrid.addData(this.restrictionInfoRecord);
		this.userComparisonGrid.addData(this.revertCommitsRecord);
		this.userComparisonGrid.addData(this.commentsPerCommitRecord);
		this.userComparisonGrid.addData(this.userDiscussionRecord);
		this.userComparisonGrid.addData(this.selfDiscussionRecord);
		this.userComparisonGrid.addData(this.similarityRecord);
		this.userComparisonGridContainer = new VLayout();
		this.userComparisonGridContainer.setWidth("60%");
		this.userComparisonGridContainer.setHeight100();
		this.userComparisonGridContainer.addMembers(this.comparisonGridLabel, this.userComparisonGrid);

		this.userComparisonAnaContainer = new HLayout();
		this.userComparisonAnaContainer.addMember(this.userComparisonGridContainer);
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
		this.bindCommitsPerDayRecord();
		this.bindUserclassSelfDiscussionRecord();
		this.bindUserclassRevertRecord();
		this.bindCommentsPerCommitRecord();
		this.bindUserclassUserDiscussionRecord();
		this.bindSimilarCategoryRecord();
		this.bindSimilarArticlesRecord();
		this.bindAbusesRecord();
		this.bindSimilarityRecordRecord();
		this.bindArticleAmountInfoRecord();
		this.bindIsBotRecorRecord();
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
				signUpInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimRegDate()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUsersCommitsRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				commitsInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getTotalCommits()+" ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassNumOfCommits()+")");
				commitsInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getTotalCommits()+" ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassNumOfCommits()+")");
				commitsInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimCommits()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}

	
	private void bindCommitsPerDayRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				commitsPerDayRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getCommitsPerDay() + " ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassAvgCommits()+")");
				commitsPerDayRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getCommitsPerDay() + " ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassAvgCommits()+")");
				commitsPerDayRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimComPDay()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindReputationInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				reputationInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getReputation());
				reputationInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getReputation());
				reputationInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimRep()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindCategoryAmountInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				categoryAmountInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getCategoryCommits());
				categoryAmountInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getCategoryCommits());
				categoryAmountInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimCatRatio()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}

	private void bindIsBotRecorRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				isBotRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().isBot());
				isBotRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().isBot());
				isBotRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getUserInfo1()
																			  .isBot() == presenter.getUserComparisonInfo().getUserInfo2().isBot());
				userComparisonGrid.refreshFields();
			}
		});
	}

	private void bindArticleAmountInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				articleAmountInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getEditedCategories().size());
				articleAmountInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getEditedCategories().size());
				articleAmountInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimArtRatio()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindRestrictionInfoRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				restrictionInfoRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().isBlocked());
				restrictionInfoRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().isBlocked());
				restrictionInfoRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimBlock()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassRevertRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				revertCommitsRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getRevertCommitsRatio()+" ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassReverts()+")");
				revertCommitsRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getRevertCommitsRatio()+" ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassReverts()+")");
				revertCommitsRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimRevCom()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindCommentsPerCommitRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				commentsPerCommitRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getCommentsPerCommitRatio() + " ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassCleaness()+")");
				commentsPerCommitRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getCommentsPerCommitRatio() + " ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassCleaness()+")");
				commentsPerCommitRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimCommeCom()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassUserDiscussionRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				userDiscussionRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getNumOfUserDiscussion()+" ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassUserDiscussion()+")");
				userDiscussionRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getNumOfUserDiscussion()+" ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassUserDiscussion()+")");
				userDiscussionRecord.setAttribute(congruarityValue.getName(), + presenter.getUserComparisonInfo().getSimDisOth()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindUserclassSelfDiscussionRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				selfDiscussionRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getNumOfSelfDiscussion()+" ("+presenter.getUserComparisonInfo().getUserInfo1().getUserclassSelfDiscussion()+")");
				selfDiscussionRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getNumOfSelfDiscussion()+" ("+presenter.getUserComparisonInfo().getUserInfo2().getUserclassSelfDiscussion()+")");
				selfDiscussionRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimDisSelf()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindSimilarityRecordRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				similarityRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimilarityRatio()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindSimilarArticlesRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				similarArticlesRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getCongruentArticles());
				similarArticlesRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getCongruentArticles());
				similarArticlesRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getArticleCooperationRatio()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindSimilarCategoryRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				similarCategoryRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getCongruentCategories());
				similarCategoryRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getCongruentCategories());
				similarCategoryRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getCategoryCooperationRatio()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
	
	private void bindAbusesRecord() {
		this.presenter.userComparisonInfoChanged().addHandler(new Handler<EventArgs>() {

			@Override
			public void invoke(Object sender, EventArgs e) {
				abusesRecord.setAttribute(userOneValue.getName(), presenter.getUserComparisonInfo().getUserInfo1().getAbusesDescription());
				abusesRecord.setAttribute(userTwoValue.getName(), presenter.getUserComparisonInfo().getUserInfo2().getAbusesDescription());
				abusesRecord.setAttribute(congruarityValue.getName(), presenter.getUserComparisonInfo().getSimAbuse()+" %");
				userComparisonGrid.refreshFields();
			}
		});
	}
}
