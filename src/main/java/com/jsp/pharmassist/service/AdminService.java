package com.jsp.pharmassist.service;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.exception.AdminNotAuthenticatedException;
import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.mapper.AdminMapper;
import com.jsp.pharmassist.repository.AdminRepository;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.security.AuthUtils;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	private final AdminMapper adminMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthUtils authUtils;

	public AdminService(AdminRepository adminRepository, AdminMapper adminMapper, PasswordEncoder passwordEncoder,
			AuthUtils authUtils) {
		super();
		this.adminRepository = adminRepository;
		this.adminMapper = adminMapper;
		this.passwordEncoder = passwordEncoder;
		this.authUtils = authUtils;
	}

	public AdminResponse saveAdmin(AdminRequest adminRequest) {
		
		if(authUtils.isAuthenticated())
			throw new AdminNotAuthenticatedException("Admin is not authenticated to register");
		else {
		Admin admin = adminMapper.mapToAdmin(adminRequest,new Admin());
		admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
		adminRepository.save(admin);
		
		return adminMapper.mapToAdminResponse(admin);
		}
	}

	public List<AdminResponse> findAllAdmins() {
	     return	adminRepository.findAll()
                               .stream()
                               .map(adminMapper::mapToAdminResponse)
                               .toList();		
	}

	public AdminResponse findAdmin(String adminId) {

      return  adminRepository.findById(adminId)
                             .map(adminMapper::mapToAdminResponse)
                             .orElseThrow(() -> new AdminNotFoundByIdException("Failed to find admin"));
	}


	public AdminResponse updateAdmin(AdminRequest adminRequest, String adminId) {
		
		return adminRepository.findById(adminId)
                .map(exAdmin -> {
       	          adminMapper.mapToAdmin(adminRequest, exAdmin);
       	          return adminRepository.save(exAdmin);
                })
                .map(adminMapper::mapToAdminResponse)
                .orElseThrow(() -> new AdminNotFoundByIdException("Failed to update admin"));
}
	
	



	

}
