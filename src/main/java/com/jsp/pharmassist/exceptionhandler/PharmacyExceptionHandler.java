package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.NoPharmacyFoundException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ErrorStructure;

@RestControllerAdvice
public class PharmacyExceptionHandler {
	
	private final AppResponseBuilder responseBuilder;

	public PharmacyExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}

	@ExceptionHandler(NoPharmacyFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoPharmacyFound(NoPharmacyFoundException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"No pharmacies found based on the criteria");
	}
	
	@ExceptionHandler(PharmacyNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handlePharmacyFoundNotById(PharmacyNotFoundByIdException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"No pharmacies found based on the id");
	}
	

}
