package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.InvalidDataException;
import com.jsp.pharmassist.exception.InvalidDateFormatException;
import com.jsp.pharmassist.exception.InvalidFileFormatException;
import com.jsp.pharmassist.exception.NoMedicinesFoundException;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ErrorStructure;

@RestControllerAdvice
public class MedicineExceptionHandler {
	
	private final AppResponseBuilder responseBuilder;

	public MedicineExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ErrorStructure> handleInvalidDateFormat(InvalidDateFormatException ex) {
		
		return responseBuilder.error(HttpStatus.BAD_REQUEST,ex.getMessage(),"Date is not in the format of yyyy-mm-dd");
	}
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorStructure> handleInvalidData(InvalidDataException ex) {
		
		return responseBuilder.error(HttpStatus.BAD_REQUEST,ex.getMessage(),"Data is in invalid format");
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	public ResponseEntity<ErrorStructure> handleInvalidFileFormat(InvalidFileFormatException ex) {
		
		return responseBuilder.error(HttpStatus.BAD_REQUEST,ex.getMessage(),"File should be in the .xlxs format");
	}
	
	@ExceptionHandler(NoMedicinesFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoMedicinesFound(NoMedicinesFoundException ex) {
		
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"Medicines not found based on the given name or ingredients");
	}

}
