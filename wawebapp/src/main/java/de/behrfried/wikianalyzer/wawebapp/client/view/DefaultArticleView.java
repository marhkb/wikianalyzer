package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;

/**
 * Default implementation of {@link ArticleView}.
 * @author marcus
 *
 */
public class DefaultArticleView implements ArticleView {
    
    /**
     * the parent element of the {@link DefaultArticleView} where the widgets has to be put in.
     */
    private final StartPageView parentView;
    
    @Inject
    public DefaultArticleView(StartPageView parentView) {
	this.parentView = parentView;
    }
    
    /**
     * @see View
     */
    public void init() {
	// TODO Auto-generated method stub
    }
    
    /**
     * @see View
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }
    
}
