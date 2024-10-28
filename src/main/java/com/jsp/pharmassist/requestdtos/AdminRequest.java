package com.jsp.pharmassist.requestdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AdminRequest {

	@NotNull(message="email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "Invail email Id")
	private String adminEmail;
	
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", 
    message ="password which must contain atleast 1 Upper case letter, 1 Lower case letter, 1 numeric character, 1 special character and length should maximum be 12 and minimum should be 8")
	private String adminPassword;
	
	@NotNull(message="MobileNumber cannot be null")
	@NotBlank(message = "MobileNumber cannot be blank")
	@Pattern(regexp = "^(?:\\+91|91|0)?[789]\\d{9}$", message = "Invalid Mobile number")
	private String adminPhoneNumber;

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}

	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}
	
	

}
