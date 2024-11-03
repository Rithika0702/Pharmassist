package com.jsp.pharmassist.service;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.exception.NoPharmacyFoundException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
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

	public PharmacyResponse findPharmacyByAdminId(String adminId) {
		
		Admin admin = adminRepository.findById(adminId)
				                     .orElseThrow(() -> new AdminNotFoundByIdException("Failed to find Admin by Id"));

		Pharmacy pharmacy = adminRepository.findPharmacyByAdminId(adminId);
		if(pharmacy == null)
		{
			throw new NoPharmacyFoundException("No Pharmacy associated with admin ID:"+adminId);
		}
		return pharmacyMapper.mapToPharmacyResponse(pharmacy);

//		return adminRepository.findById(adminId)
//						.map(Admin::getPharmacy)  // Extract the pharmacy from the admin
//						.map(pharmacyMapper::mapToPharmacyResponse)  // Map Pharmacy to PharmacyResponse
//						.orElseThrow(() -> new PharmacyNotFoundException("No Pharmacy associated with admin ID:"+adminId));

	}

	public PharmacyResponse updatePharmacy(PharmacyRequest pharmacyRequest, String pharmacyId) {
		
		return pharmacyRepository.findById(pharmacyId)
				                 .map(exPharmacy ->{
					                    pharmacyMapper.mapToPharmacy(pharmacyRequest, exPharmacy);
					                    return pharmacyRepository.save(exPharmacy);
				                 })
				                 .map(pharmacyMapper::mapToPharmacyResponse)
				                 .orElseThrow(() -> new PharmacyNotFoundByIdException("Failed to update Pharmacy"));
	}

	
	

}
