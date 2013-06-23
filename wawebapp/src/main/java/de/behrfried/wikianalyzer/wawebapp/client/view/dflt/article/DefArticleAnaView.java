package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.ScatterChart;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefArticleAnaView extends ArticleAnaView {

	private final static String AUTHOR_GRID_AUTHOR = "authorGridAuthor";
	private final static String AUTHOR_GRID_COMMITS = "authorGridCommits";
	private final static String AUTHOR_GRID_QUANTITY = "authorGridQuantity";
	private final static String ARTICLE_GRID_REVISION = "articleGridRevision";
	private final static String ARTICLE_GRID_PARENT = "articleGridParent";
	private final static String ARTICLE_GRID_DATE = "articleGridDate";
	private final static String ARTICLE_GRID_AUTHOR = "articleGridAuthor";
	private final static String ARTICLE_GRID_BYTES = "articleGridQuantity";
	private final static String ARTICLE_GRID_DIFF = "articleGridAmount";
	private final static String ARTICLE_GRID_COMMENT = "articleGridEditType";
	private final Presenter presenter;
	private final Messages messages;
	private HTMLPanel authorAnaChartContainer, articleAnaChartContainer;
	private Label authorAnaLabel, articleAnaLabel, categoryAnaLabel;
	private VLayout articleAnaContainer;
	private HLayout authorAnaLayout, articleAnaLayout, categoryAnaLayout;
	private ListGrid authorGrid, articleGrid, categoryGrid, similarCategoryGrid;
	private ListGridField authorGridAuthor, authorGridCommits, authorGridQuantity, articleGridRevision,
			articleGridParent,
			articleGridDate, articleGridAuthor,
			articleGridQuantity, articleGridAmount, articleGridEditType, categoryGridFrom, categoryGridTill, categoryGridCategory,
			simCatGridSimilarArticle, simCatGridCategory, simCatGridCreationDate;
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
		this.articleGridQuantity = new ListGridField(ARTICLE_GRID_BYTES, "Bytes");
		this.articleGridAmount = new ListGridField(ARTICLE_GRID_DIFF, "Diff");
		this.articleGridEditType = new ListGridField(ARTICLE_GRID_COMMENT, "Comment");
		this.articleGrid = new ListGrid();
		this.articleGrid.setFields(
				this.articleGridRevision, this.articleGridParent, this.articleGridDate, this.articleGridAuthor,
				this.articleGridQuantity, this.articleGridAmount, this.articleGridEditType
		);
		this.articleGrid.setWidth("50%");
		this.articleAnaLayout = new HLayout();
		this.articleAnaLayout.addMember(this.articleAnaChartContainer);
		this.articleAnaLayout.addMember(this.articleGrid);
		this.articleAnaLayout.setMargin(5);
		this.articleAnaLayout.setHeight(200);

		this.categoryAnaLabel = new Label("Analyse zur Artikelkategorie");
		this.categoryAnaLabel.setAlign(Alignment.CENTER);
		this.categoryAnaLabel.setHeight(10);
		this.categoryGridCategory = new ListGridField("categoryName", "Kategorien");
		this.categoryGridFrom = new ListGridField("categoryFrom", "Von");
		this.categoryGridTill = new ListGridField("categoryTill", "Bis");
		this.categoryGrid = new ListGrid();
		this.categoryGrid.setFields(this.categoryGridCategory, this.categoryGridFrom, this.categoryGridTill);
		this.categoryGrid.setWidth("50%");
		this.simCatGridSimilarArticle = new ListGridField("simCatArticleName", "Name des ähnlichen Artikels");
		this.simCatGridCategory = new ListGridField("simCatCategory", "Ähnliche Kategorien zum Artikel");
		this.simCatGridCreationDate = new ListGridField("simCatDate", "Erscheinungsdatum");
		this.similarCategoryGrid = new ListGrid();
		this.similarCategoryGrid
				.setFields(this.simCatGridSimilarArticle, this.simCatGridCategory, this.simCatGridCreationDate);
		this.similarCategoryGrid.setWidth("50%");
		this.categoryAnaLayout = new HLayout();
		this.categoryAnaLayout.addMembers(this.categoryGrid, this.similarCategoryGrid);
		this.categoryAnaLayout.setMargin(5);
		this.categoryAnaLayout.setHeight("30%");

		this.articleAnaContainer = new VLayout();
		this.articleAnaContainer.addMembers(
				this.authorAnaLabel, this.authorAnaLayout, this.articleAnaLabel, this.articleAnaLayout,
				this.categoryAnaLabel, this.categoryAnaLayout
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
								data.addColumn(AbstractDataTable.ColumnType.STRING, "Task");
								data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Commits");

								final List<ArticleInfo.AuthorAndCommits> authorAndCommits =
										presenter.getArticleInfo().getAuthorsAndCommits();
								Collections.sort(
										authorAndCommits, new Comparator<ArticleInfo.AuthorAndCommits>() {
									@Override
									public int compare(ArticleInfo.AuthorAndCommits authorAndCommits,
													   ArticleInfo.AuthorAndCommits authorAndCommits2) {
										return authorAndCommits2.getNumOfCommits() - authorAndCommits.getNumOfCommits();
									}
								}
								);

								final int rows = Math.min(10, authorAndCommits.size());
								data.addRows(rows);

								for(int i = 0; i < rows; i++) {
									data.setValue(i, 0, authorAndCommits.get(i).getAuthor());
									data.setValue(i, 1, authorAndCommits.get(i).getNumOfCommits());
								}
								ColumnChart.Options options = ColumnChart.Options.create();
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
							lgr.setAttribute(AUTHOR_GRID_AUTHOR, aac.getAuthor());
							lgr.setAttribute(AUTHOR_GRID_COMMITS, aac.getNumOfCommits());
							lgr.setAttribute(
									AUTHOR_GRID_QUANTITY,
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
								data.addColumn(AbstractDataTable.ColumnType.STRING, "Task");
								data.addColumn(AbstractDataTable.ColumnType.NUMBER, "Hours per Day");
								data.addRows(2);
								data.setValue(0, 0, "Work");
								data.setValue(0, 1, 14);
								data.setValue(1, 0, "Sleep");
								data.setValue(1, 1, 10);
								ScatterChart.Options options = ScatterChart.Options.create();
								options.setWidth(500);
								options.setHeight(250);
								options.setTitle("My Daily Activities");
								ScatterChart chart = new ScatterChart(data, options);
								articleAnaChartContainer.add(chart);
							}
						};
						VisualizationUtils.loadVisualizationApi(r, ScatterChart.PACKAGE);
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
							lgr.setAttribute(ARTICLE_GRID_REVISION, rev.getRevid());
							lgr.setAttribute(ARTICLE_GRID_PARENT, rev.getParentid());
							lgr.setAttribute(ARTICLE_GRID_DATE, rev.getTimestamp());
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
}
