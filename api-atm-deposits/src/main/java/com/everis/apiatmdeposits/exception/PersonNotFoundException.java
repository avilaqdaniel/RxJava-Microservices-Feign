package com.everis.apiatmdeposits.exception;

public class PersonNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super("Person not found");
	}
}
