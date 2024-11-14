package com.jsp.pharmassist.exception;

public class AdminNotAuthenticatedException extends RuntimeException{
	
	private final String message;
	
	public AdminNotAuthenticatedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

	
	
	

}
