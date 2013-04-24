package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public interface ArticleView extends View {
    public Label getWaLabel();
    public SuggestBox getSearchBox();
    public Button getSearchButton();
    public MenuBar getMenuBar();
    public StackLayoutPanel getWikiPrevPanel();
}
