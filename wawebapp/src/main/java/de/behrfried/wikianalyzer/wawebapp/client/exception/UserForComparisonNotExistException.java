package de.behrfried.wikianalyzer.wawebapp.client.exception;

import java.io.Serializable;


public class UserForComparisonNotExistException extends Exception implements Serializable {

	public UserForComparisonNotExistException() { }

	public UserForComparisonNotExistException(String message) {
		super(message);
	}
}
