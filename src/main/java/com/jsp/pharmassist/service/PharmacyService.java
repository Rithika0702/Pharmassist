package com.jsp.pharmassist.service;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.mapper.PharmacyMapper;
import com.jsp.pharmassist.repository.AdminRepository;
import com.jsp.pharmassist.repository.PharmacyRepository;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmacyResponse;

@Service
public class PharmacyService {
	
	private final PharmacyRepository pharmacyRepository;
	private final PharmacyMapper pharmacyMapper;
	private final AdminRepository adminRepository;
	
	public PharmacyService(PharmacyRepository pharmacyRepository, PharmacyMapper pharmacyMapper,AdminRepository adminRepository) {
		super();
		this.pharmacyRepository = pharmacyRepository;
		this.pharmacyMapper = pharmacyMapper;
		this.adminRepository = adminRepository;
	}
	
    public PharmacyResponse addPharmacy(PharmacyRequest pharmacyRequest,String adminId) {
	
    return adminRepository.findById(adminId)
    		              .map( admin -> {
    					      Pharmacy pharmacy = pharmacyRepository
    					    		  .save(pharmacyMapper.mapToPharmacy(pharmacyRequest, new Pharmacy()));
    					      admin.setPharmacy(pharmacy);
    					      adminRepository.save(admin);
    					      return pharmacyMapper.mapToPharmacyResponse(pharmacy);				
    				      })
    				     .orElseThrow(() ->new AdminNotFoundByIdException("Failed to find Admin"));
	}

	
	

}
