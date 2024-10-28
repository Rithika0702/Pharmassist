package com.jsp.pharmassist.service;
import org.springframework.stereotype.Service;

import com.jsp.pharmassist.controller.AdminController;
import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.mapper.AdminMapper;
import com.jsp.pharmassist.repository.AdminRepository;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;

import jakarta.validation.Valid;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	private final AdminMapper adminMapper;
	
	public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
		super();
		this.adminRepository = adminRepository;
		this.adminMapper = adminMapper;
	}
	
	public AdminResponse saveAdmin(AdminRequest adminRequest) {
		
		Admin admin = adminRepository.save(adminMapper.mapToAdmin(adminRequest,new Admin()));
		return adminMapper.mapToAdminResponse(admin);
	}
	



	

}
