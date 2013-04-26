package de.behrfried.wikianalyzer.wawebapp.client.event;

public class FieldChangedEventArgs extends EventArgs {

	private final String fieldName;

	public FieldChangedEventArgs(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	
}
