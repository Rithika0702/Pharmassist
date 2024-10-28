package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;
import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;

@Component
public class AdminMapper {
	
	public Admin mapToAdmin(AdminRequest adminRequest, Admin admin) {
		
		admin.setAdminEmail(adminRequest.getAdminEmail());
		admin.setAdminPassword(adminRequest.getAdminPassword());
		admin.setAdminPhoneNumber(adminRequest.getAdminPhoneNumber());
		
		return admin;
	}
	
	public AdminResponse mapToAdminResponse(Admin admin) {
		
		AdminResponse response = new AdminResponse();
		response.setAdminId(admin.getAdminId());
		response.setAdminEmail(admin.getAdminEmail());
		
		return response;
	}

}
