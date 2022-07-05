package com.revature.exceptions;

// Checked exception AKA compile time exception, so we have to extend Exception
// If we wanted unchecked exception AKA runtime exception, we would extend RuntimeException
public class MyCheckedException extends Exception {

	private static final long serialVersionUID = 3635835703331824370L;

	// constructor - this will call super constructor from Exception
		// which calls super constructor from Throwable
	
	public MyCheckedException(String message) {
		super(message);
	}
	
	// This constructor is to return a String exception message (known as stack trace)
}
