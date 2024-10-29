package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.exception.NoAdminsFoundException;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ErrorStructure;

@RestControllerAdvice
public class AdminExceptionHandler {
	
	private final AppResponseBuilder responseBuilder;

	public AdminExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}
	
	@ExceptionHandler(NoAdminsFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoAdminsFound(NoAdminsFoundException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"No admins found based on the criteria");
	}
	
	@ExceptionHandler(AdminNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handleAdminNotFoundById(AdminNotFoundByIdException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"Admin is not found by Id");
	}


}
