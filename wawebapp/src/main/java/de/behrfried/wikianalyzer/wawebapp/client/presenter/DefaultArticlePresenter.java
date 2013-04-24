package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;

/**
 * Default implementation for {@link ArticlePresenter}.
 * @author marcus
 *
 */
public class DefaultArticlePresenter implements ArticlePresenter {
    
    /**
     * The {@link DefaultArticlePresenter}'s {@link ArticleView}
     */
    private final ArticleView view;
    
    /**
     * Creates a {@link DefaultArticlePresenter}. All parameters are injected by Gin.
     * @param view the {@link DefaultArticlePresenter}'s {@link ArticleView}
     */
    @Inject
    public DefaultArticlePresenter(ArticleView view) {
	this.view = view;
    }
    
    /**
     * @see Presenter
     */
    public void init() {
	this.view.init();
	
    }
    
    /**
     * @see Presenter
     */
    public void dispose() {
	this.view.dispose();
    }
    
}
