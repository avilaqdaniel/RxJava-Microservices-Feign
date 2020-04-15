package com.everis.apiatmdeposits.exception;

public class BlacklistedPersonException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlacklistedPersonException() {
		super("Person is blacklisted.");
	}
}
