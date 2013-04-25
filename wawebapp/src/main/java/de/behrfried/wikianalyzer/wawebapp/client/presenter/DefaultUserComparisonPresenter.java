package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.gwt.user.client.ui.Panel;
import com.google.inject.Inject;

import de.behrfried.wikianalyzer.wawebapp.client.view.UserComparisonView;

/**
 * Default implementation of {@link UserComparisonPresenter}
 * @author marcus
 *
 */
public class DefaultUserComparisonPresenter implements UserComparisonPresenter {
   
    
    /**
     * Creates an instance of {@link DefaultUserComparisonPresenter}. All arguments are injected by Gin.
     * @param view the {@link DefaultUserComparisonPresenter}'s {@link UserComparisonView}
     * @throws IllegalArgumentException if view == null
     */
    @Inject
    public DefaultUserComparisonPresenter()  {
    }
    
}
