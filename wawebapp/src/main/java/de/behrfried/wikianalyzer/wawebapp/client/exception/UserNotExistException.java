package de.behrfried.wikianalyzer.wawebapp.client.exception;

import java.io.Serializable;


public class UserNotExistException extends Exception implements Serializable {

	public UserNotExistException() { }

	public UserNotExistException(String message) {
		super(message);
	}
}
