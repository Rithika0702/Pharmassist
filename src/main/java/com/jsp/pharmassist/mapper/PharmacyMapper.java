package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmacyResponse;

@Component
public class PharmacyMapper {
	
	public Pharmacy mapToPharmacy(PharmacyRequest pharmacyRequest, Pharmacy pharmacy) {
		
		pharmacy.setName(pharmacyRequest.getName());
		pharmacy.setGstNumber(pharmacyRequest.getGstNumber());
		pharmacy.setLicenseNumber(pharmacyRequest.getLicenseNumber());
		
		return pharmacy;
	}
	
	public PharmacyResponse mapToPharmacyResponse(Pharmacy pharmacy) {
		
			PharmacyResponse response = new PharmacyResponse();
			
			response.setPharmacyId(pharmacy.getPharmacyId());
			response.setName(pharmacy.getName());
			response.setGstNumber(pharmacy.getGstNumber());
			response.setLicenseNumber(pharmacy.getLicenseNumber());

			return response;
	}
	

}
