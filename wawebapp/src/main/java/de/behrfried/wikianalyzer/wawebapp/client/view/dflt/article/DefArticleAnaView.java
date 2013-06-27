package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.gwt.i18n.client.NumberFormat;
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
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;

import java.util.List;

public class DefArticleAnaView extends ArticleAnaView {

	private final static String AUTHOR_GRID_AUTHOR = "authorGridAuthor";
	private final static String AUTHOR_GRID_COMMITS = "authorGridCommits";
	private final static String AUTHOR_GRID_QUANTITY = "authorGridQuantity";
	private final static String ARTICLE_GRID_REVISION = "articleGridRevision";
	private final static String ARTICLE_GRID_PARENT = "articleGridParent";
	private final static String ARTICLE_GRID_DATE = "articleGridDate";
	private final static String ARTICLE_GRID_AUTHOR = "articleGridAuthor";
	private final static String ARTICLE_GRID_BYTES = "articleGridBytes";
	private final static String ARTICLE_GRID_DIFF = "articleGridDiff";
	private final static String ARTICLE_GRID_COMMENT = "articleGridComment";
	private final Presenter presenter;
	private final Messages messages;
	private HTMLPanel authorAnaChartContainer, articleAnaChartContainer;
	private Label authorAnaLabel, articleAnaLabel, categoryAnaLabel, editWarLabel;
	private VLayout articleAnaContainer;
	private HLayout authorAnaLayout, articleAnaLayout, categoryAnaLayout, editWarAnaLayout;
	private ListGrid authorGrid, articleGrid, categoryGrid, similarCategoryGrid, editWarGrid;
	private ListGridField authorGridAuthor, authorGridCommits, authorGridQuantity, articleGridRevision,
			articleGridParent,
			articleGridDate, articleGridAuthor,
			articleGridBytes, articleGridDiff, articleGridComment, categoryGridFrom, categoryGridTill, categoryGridCategory,
			simCatGridSimilarArticle, simCatGridCategory, simCatGridCreationDate, editWarFrom, editWarTill, editWarUser;
	private HTMLPanel chartContainer;

	@Inject
	public DefArticleAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.init();
	}

	private void init() {
		this.authorAnaLabel = new Label("Analyse der am Artikel mitwirkenden Authoren");
		this.authorAnaLabel.setAlign(Alignment.CENTER);
		this.authorAnaLabel.setHeight(10);
		this.authorAnaChartContainer = new HTMLPanel("");
		this.authorAnaChartContainer.setWidth("50%");
		this.authorAnaChartContainer.setHeight("50%");

		this.authorGridAuthor = new ListGridField(AUTHOR_GRID_AUTHOR, "Author");
		this.authorGridCommits = new ListGridField(AUTHOR_GRID_COMMITS, "Einsendungen");
		this.authorGridQuantity = new ListGridField(AUTHOR_GRID_QUANTITY, "Verhältnis Einsendungen/Text");
		this.authorGrid = new ListGrid();
		this.authorGrid.setFields(this.authorGridAuthor, this.authorGridCommits, this.authorGridQuantity);
		this.authorGrid.setWidth("50%");
		this.authorAnaLayout = new HLayout();
		this.authorAnaLayout.addMember(this.authorAnaChartContainer);
		this.authorAnaLayout.addMember(this.authorGrid);
		this.authorAnaLayout.setMargin(5);
		this.authorAnaLayout.setHeight(200);

		this.articleAnaLabel = new Label("Analyse zum Artikel");
		this.articleAnaLabel.setAlign(Alignment.CENTER);
		this.articleAnaLabel.setHeight(10);
		this.articleAnaChartContainer = new HTMLPanel("");
		this.articleAnaChartContainer.setWidth("50%");
		this.articleAnaChartContainer.setHeight("50%");

		this.articleGridRevision = new ListGridField(ARTICLE_GRID_REVISION, "Revision");
		this.articleGridParent = new ListGridField(ARTICLE_GRID_PARENT, "Parent");
		this.articleGridDate = new ListGridField(ARTICLE_GRID_DATE, "Änderungsdatum");
		this.articleGridAuthor = new ListGridField(ARTICLE_GRID_AUTHOR, "Author");
		this.articleGridBytes = new ListGridField(ARTICLE_GRID_BYTES, "Bytes");
		this.articleGridDiff = new ListGridField(ARTICLE_GRID_DIFF, "Diff");
		this.articleGridComment = new ListGridField(ARTICLE_GRID_COMMENT, "Comment");
		this.articleGrid = new ListGrid();
		this.articleGrid.setFields(
				this.articleGridRevision, this.articleGridParent, this.articleGridDate, this.articleGridAuthor,
				this.articleGridBytes, this.articleGridDiff, this.articleGridComment
		);
		this.articleGrid.setWidth("50%");
		this.articleAnaLayout = new HLayout();
		this.articleAnaLayout.addMember(this.articleAnaChartContainer);
		this.articleAnaLayout.addMember(this.articleGrid);
		this.articleAnaLayout.setMargin(5);
		this.articleAnaLayout.setHeight(230);

		this.categoryAnaLabel = new Label("Analyse zur Artikelkategorie");
		this.categoryAnaLabel.setAlign(Alignment.CENTER);
		this.categoryAnaLabel.setHeight(10);
		this.categoryGridCategory = new ListGridField("categoryName", "Kategorien");
		this.categoryGridFrom = new ListGridField("categoryFrom", "Von");
		this.categoryGridTill = new ListGridField("categoryTill", "Bis");
		this.categoryGrid = new ListGrid();
		this.categoryGrid.setHeight(230);
		this.categoryGrid.setFields(this.categoryGridCategory, this.categoryGridFrom, this.categoryGridTill);
		this.categoryGrid.setWidth("50%");
		this.simCatGridSimilarArticle = new ListGridField("simCatArticleName", "Name des ähnlichen Artikels");
		this.simCatGridCategory = new ListGridField("simCatCategory", "Ähnliche Kategorien zum Artikel");
		this.simCatGridCreationDate = new ListGridField("simCatDate", "Erscheinungsdatum");
		this.similarCategoryGrid = new ListGrid();
		this.similarCategoryGrid.setHeight(230);
		this.similarCategoryGrid
				.setFields(this.simCatGridSimilarArticle, this.simCatGridCategory, this.simCatGridCreationDate);
		this.similarCategoryGrid.setWidth("50%");
		this.categoryAnaLayout = new HLayout();
		this.categoryAnaLayout.addMembers(this.categoryGrid, this.similarCategoryGrid);
		this.categoryAnaLayout.setMargin(5);
		this.categoryAnaLayout.setHeight(230);
		
		this.editWarLabel = new Label("Edit Wars dieses Artikels");
		this.editWarLabel.setHeight(20);
		this.editWarFrom = new ListGridField("warFrom","Von");
		this.editWarTill = new ListGridField("warTill","Bis");
		this.editWarUser = new ListGridField("warUser","Beteiligte Nutzer");
		this.editWarGrid = new ListGrid();
		this.editWarGrid.setHeight(230);
		this.editWarGrid.setFields(this.editWarFrom, this.editWarTill,this.editWarUser);
		this.editWarAnaLayout = new HLayout();
		this.editWarAnaLayout.addMember(this.editWarGrid);
		

		this.articleAnaContainer = new VLayout();
		this.articleAnaContainer.addMembers(
				this.authorAnaLabel, this.authorAnaLayout, this.articleAnaLabel, this.articleAnaLayout,
				this.categoryAnaLabel, this.categoryAnaLayout, this.editWarLabel, this.editWarAnaLayout
		);
		this.articleAnaContainer.setMargin(10);
		this.articleAnaContainer.setHeight100();
		this.articleAnaContainer.setWidth100();

		this.addChild(this.articleAnaContainer);

		this.bind();
	}

	private void bind() {
		this.bindAuthorChart();
		this.bindAuthorGrid();
		this.bindArticleChart();
		this.bindArticleGrid();
		this.bindSimilarCategoryGrid();
        this.bindEditWarGrid();
	}

	private void bindAuthorChart() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs eventArgs) {
						authorAnaChartContainer.clear();
						Runnable r = new Runnable() {
							@Override
							public void run() {
								DataTable data = DataTable.create();
								data.addColumn(AbstractDataTable.ColumnType.STRING, "User");
								data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Commits insgesamt");

								final List<ArticleInfo.AuthorAndCommits> authorAndCommits =
										presenter.getArticleInfo().getAuthorsAndCommits();

								final int rows = Math.min(10, authorAndCommits.size());
								data.addRows(rows);

								for(int i = 0; i < rows; i++) {
									data.setValue(i, 0, authorAndCommits.get(i).getAuthor());
									data.setValue(i, 1, authorAndCommits.get(i).getNumOfCommits());
								}
								ColumnChart.Options options = ColumnChart.Options.create();
								options.setShowCategories(false);
								options.setWidth(500);
								options.setHeight(250);
								options.set3D(true);
								options.setTitle("Top 10 Committer");
								ColumnChart chart = new ColumnChart(data, options);
								authorAnaChartContainer.add(chart);
							}
						};
						VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
					}
				}
		);
	}

	private void bindAuthorGrid() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs eventArgs) {
						while(authorGrid.getRecordList().getLength() > 0) {
							authorGrid.removeData(authorGrid.getRecord(0));
						}
						for(final ArticleInfo.AuthorAndCommits aac : presenter.getArticleInfo().getAuthorsAndCommits()) {
							final ListGridRecord lgr = new ListGridRecord();
							lgr.setAttribute(authorGridAuthor.getName(), aac.getAuthor());
							lgr.setAttribute(authorGridCommits.getName(), aac.getNumOfCommits());
							lgr.setAttribute(
									authorGridQuantity.getName(),
									NumberFormat.getPercentFormat().format(
											aac.getNumOfCommits() / (double)presenter.getArticleInfo()
																					 .getAuthorsAndCommits().size()
									)
							);

							authorGrid.addData(lgr);
						}
					}
				}
		);
	}

	private void bindArticleChart() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs eventArgs) {
						articleAnaChartContainer.clear();
						Runnable r = new Runnable() {
							@Override
							public void run() {
								DataTable data = DataTable.create();
								data.addColumn(AbstractDataTable.ColumnType.DATE, "Datum");
								data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Anzahl Commits");

								final List<ArticleInfo.RevsPerDate> authorAndCommits =
										presenter.getArticleInfo().getRevsPerDates();
								final int rows = authorAndCommits.size();
								data.addRows(rows);

								for(int i = 0; i < rows; i++) {
									data.setValue(i, 0, authorAndCommits.get(i).getDate());
									data.setValue(i, 1, authorAndCommits.get(i).getNumOfRevs());
								}
								ColumnChart.Options options = ColumnChart.Options.create();
								options.setShowCategories(false);
								options.setWidth(500);
								options.setHeight(200);
								options.setTitle("Revisionsverlauf");
								ColumnChart chart = new ColumnChart(data, options);
								articleAnaChartContainer.add(chart);
							}
						};
						VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
					}
				}
		);
	}

	private void bindArticleGrid() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs eventArgs) {
						while(articleGrid.getRecordList().getLength() > 0) {
							articleGrid.removeData(articleGrid.getRecord(0));
						}

						for(final ArticleInfo.Revision rev : presenter.getArticleInfo().getRevisions()) {
							final ListGridRecord lgr = new ListGridRecord();
							lgr.setAttribute(articleGridRevision.getName(), rev.getRevid());
							lgr.setAttribute(articleGridParent.getName(), rev.getParentid());
							lgr.setAttribute(articleGridDate.getName(), rev.getTimestamp());
							lgr.setAttribute(articleGridAuthor.getName(), rev.getAuthor());
							lgr.setAttribute(articleGridBytes.getName(), rev.getBytes());
							lgr.setAttribute(articleGridDiff.getName(), rev.getDiff());
							lgr.setAttribute(articleGridComment.getName(), rev.getComment());
							articleGrid.addData(lgr);
						}
					}
				}
		);
	}

	private void bindSimilarCategoryGrid() {
		this.presenter.articleInfoChanged().addHandler(
				new Handler<EventArgs>() {
					@Override
					public void invoke(Object sender, EventArgs eventArgs) {
						while(similarCategoryGrid.getRecordList().getLength() > 0) {
							similarCategoryGrid.removeData(similarCategoryGrid.getRecord(0));
						}

						for(final ArticleInfo.SimilarArticle rev : presenter.getArticleInfo().getSimilarArticles()) {
							final ListGridRecord lgr = new ListGridRecord();
							lgr.setAttribute(simCatGridSimilarArticle.getName(), rev.getTitle());
							lgr.setAttribute(simCatGridCreationDate.getName(), rev.getTimestamp());
							lgr.setAttribute(simCatGridCategory.getName(), rev.getCategories());

							similarCategoryGrid.addData(lgr);
						}
					}
				}
		);
	}

    /**
     *
     */
    private void bindEditWarGrid() {
        this.presenter.articleInfoChanged().addHandler(new Handler<EventArgs>() {
            @Override
            public void invoke(Object sender, EventArgs eventArgs) {
                while(editWarGrid.getRecordList().getLength() > 0) {
                    editWarGrid.removeData(editWarGrid.getRecord(0));
                }

                for(final ArticleInfo.EditWar editWar : presenter.getArticleInfo().getEditWars()) {
                    final ListGridRecord lgr = new ListGridRecord();
                    lgr.setAttribute(editWarFrom.getName(), editWar.getFrom());
                    lgr.setAttribute(editWarTill.getName(), editWar.getTo());
                    lgr.setAttribute(editWarUser.getName(), "null");
                }
            }
        });
    }
}