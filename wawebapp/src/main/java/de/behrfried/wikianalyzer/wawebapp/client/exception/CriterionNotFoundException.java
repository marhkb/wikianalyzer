package de.behrfried.wikianalyzer.wawebapp.client.exception;

import java.io.Serializable;


public class CriterionNotFoundException extends Exception implements Serializable {

	public CriterionNotFoundException() { }

	public CriterionNotFoundException(String message) {
		super(message);
	}
}
