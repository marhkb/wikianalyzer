package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user.DefaultUserView;

public class DatePickerPopUpView extends PopUpView {

	private final Presenter presenter;

	/**
	 * {@link DefaultUserView}'s parent element
	 */
	private final Messages messages;

	@Inject
	public DatePickerPopUpView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.init();
	}

	private void init() {

		this.bind();
	}

	private void bind() {

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
