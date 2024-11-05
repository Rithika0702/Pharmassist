package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.NoPatientsFoundException;
import com.jsp.pharmassist.exception.PatientNotFoundByIdException;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ErrorStructure;

@RestControllerAdvice
public class PatientExceptionHandler {

	private final AppResponseBuilder responseBuilder;

	public PatientExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}
	
	@ExceptionHandler(PatientNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handlePatientFoundNotById(PatientNotFoundByIdException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"No patients found based on the id");
	}
	
	@ExceptionHandler(NoPatientsFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoPatientsFound(NoPatientsFoundException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"No patient is associated with the pharmacy Id");
	}
}
