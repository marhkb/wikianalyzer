package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;

public class DefArticleAnaView extends ArticleAnaView {

	private final Presenter presenter;
	private final Messages messages;
	private final HTMLPanel authorAnaChartContainer, articleAnaChartContainer;
	private Label authorAnaLabel, articleAnaLabel, categoryAnaLabel;
	private VLayout articleAnaContainer;
	private HLayout authorAnaLayout, articleAnaLayout, categoryAnaLayout;
	private ListGrid authorGrid, articleGrid, categoryGrid, similarCategoryGrid;
	private ListGridField authorGridAuthor, authorGridCommits, authorGridQuantity, articleGridRevision, articleGridDate, articleGridAuthor,
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
		this.createAuthorAnaChart();
		this.authorGridAuthor = new ListGridField("authorAuthor", "Author");
		this.authorGridCommits = new ListGridField("authorCommits", "Einsendungen");
		this.authorGridQuantity = new ListGridField("authorQuantity", "Verhältnis Einsendungen/Text");
		this.authorGrid = new ListGrid();
		this.authorGrid.setFields(this.authorGridAuthor, this.authorGridCommits, this.authorGridQuantity);
		this.authorGrid.setWidth("50%");
		this.authorAnaLayout = new HLayout();
		this.authorAnaLayout.addMember(this.authorAnaChartContainer);
		this.authorAnaLayout.addMember(this.authorGrid);
		this.authorAnaLayout.setMargin(5);
		this.authorAnaLayout.setHeight("30%");

		this.articleAnaLabel = new Label("Analyse zum Artikel");
		this.articleAnaLabel.setAlign(Alignment.CENTER);
		this.articleAnaLabel.setHeight(10);
		this.articleGridRevision = new ListGridField("articleRevision", "Revision");
		this.articleGridDate = new ListGridField("articleDate", "Änderungsdatum");
		this.articleGridAuthor = new ListGridField("articleAuthor", "Author");
		this.articleGridQuantity = new ListGridField("articleQuantity", "Verhältnis Einsendung/Text");
		this.articleGridAmount = new ListGridField("articleAmount", "Anzahl Einsendungen");
		this.articleGridEditType = new ListGridField("articleEditType", "Änderungsart");
		this.articleGrid = new ListGrid();
		this.articleGrid.setFields(this.articleGridRevision, this.articleGridDate, this.articleGridAuthor, this.articleGridQuantity,
		        this.articleGridAmount, this.articleGridEditType);
		this.articleGrid.setWidth("50%");
		this.articleAnaLayout = new HLayout();
		this.articleAnaLayout.addMember(this.articleGrid);
		this.articleAnaLayout.setMargin(5);
		this.articleAnaLayout.setHeight("30%");

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
		this.similarCategoryGrid.setFields(this.simCatGridSimilarArticle, this.simCatGridCategory, this.simCatGridCreationDate);
		this.similarCategoryGrid.setWidth("50%");
		this.categoryAnaLayout = new HLayout();
		this.categoryAnaLayout.addMembers(this.categoryGrid, this.similarCategoryGrid);
		this.categoryAnaLayout.setMargin(5);
		this.categoryAnaLayout.setHeight("30%");

		this.articleAnaContainer = new VLayout();
		this.articleAnaContainer.addMembers(this.authorAnaLabel, this.authorAnaLayout, this.articleAnaLabel, this.articleAnaLayout,
		        this.categoryAnaLabel, this.categoryAnaLayout);
		this.articleAnaContainer.setMargin(10);
		this.articleAnaContainer.setHeight100();
		this.articleAnaContainer.setWidth100();

		this.addChild(this.articleAnaContainer);
	}
	
	private void createAuthorAnaChart() {
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
				options.setWidth(400);
				options.setHeight(240);
				options.set3D(true);
				options.setTitle("My Daily Activities");
				ColumnChart chart = new ColumnChart(data, options);
				authorAnaChartContainer.add(chart);
			}
		};
		VisualizationUtils.loadVisualizationApi(r, ColumnChart.PACKAGE);
	}
}
