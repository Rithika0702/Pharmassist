package com.jsp.pharmassist.exception;

public class InvalidFileFormatException extends RuntimeException {
	
	private final String message;

	public InvalidFileFormatException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
