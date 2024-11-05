package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;
import com.jsp.pharmassist.service.PatientService;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class PatientController {
	
	private final PatientService patientService;
	private final AppResponseBuilder responseBuilder;
	
	public PatientController(PatientService patientService, AppResponseBuilder responseBuilder) {
		super();
		this.patientService = patientService;
		this.responseBuilder = responseBuilder;
	}
	
	@PostMapping("/pharmacies/{pharmacyId}/patients")
	public ResponseEntity<ResponseStructure<PatientResponse>> addPatient(@RequestBody @Valid PatientRequest patientRequest, @PathVariable String pharmacyId) {
		 PatientResponse response = patientService.addPatient(patientRequest,pharmacyId);
		 return responseBuilder.success(HttpStatus.CREATED,"Patient registered",response);
	}	
	
	@PutMapping("patients/{patientId}")
	public ResponseEntity<ResponseStructure<PatientResponse>> updatePatient(@RequestBody PatientRequest patientRequest, @PathVariable String patientId) {
		
		PatientResponse response = patientService.updatePatient(patientRequest, patientId);
		return responseBuilder.success(HttpStatus.OK,"Patient Updated", response);
		
	}
}
