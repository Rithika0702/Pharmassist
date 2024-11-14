package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmacyResponse;
import com.jsp.pharmassist.service.PharmacyService;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class PharmacyController {
	
	private final PharmacyService pharmacyService;
	private final AppResponseBuilder responseBuilder;
	
	public PharmacyController(PharmacyService pharmacyService, AppResponseBuilder responseBuilder) {
		super();
		this.pharmacyService = pharmacyService;
		this.responseBuilder = responseBuilder;
	}
	
	@PostMapping("/pharmacies")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> addPharmacy(@RequestBody @Valid PharmacyRequest pharmacyRequest) {
	
		PharmacyResponse response = pharmacyService.addPharmacy(pharmacyRequest);
		return responseBuilder.success(HttpStatus.CREATED, "Pharmacy Created", response);
	}
	
	@GetMapping("/admins/{adminId}/pharmacies")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> findPharmacyByAdminId(@PathVariable String adminId)
	{
		PharmacyResponse response = pharmacyService.findPharmacyByAdminId(adminId);
		return responseBuilder.success(HttpStatus.FOUND,"Pharmacy associated with admin found",response );
	}
	
	@PutMapping("/pharmacies/{pharmacyId}")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> updatePharmacy(@RequestBody PharmacyRequest pharmacyRequest,@PathVariable String pharmacyId)
	{
		PharmacyResponse response = pharmacyService.updatePharmacy(pharmacyRequest, pharmacyId);
		return responseBuilder.success(HttpStatus.OK,"Pharmacy Updated", response);
	}
	
	

}
