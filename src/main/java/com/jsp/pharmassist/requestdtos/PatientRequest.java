package com.jsp.pharmassist.requestdtos;

import java.time.LocalDate;

import com.jsp.pharmassist.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PatientRequest {
	
	@NotNull(message = "PatientName cannot be null")
	@NotBlank(message = "PatientName cannot be blank")
	@Pattern(regexp = "^[A-Za-z0-9'\\-\\s]{3,50}$",message = "Invalid name")
	private String patientName;
	
	@NotNull(message = "PatientEmail cannot be null")
	@NotBlank(message = "PatientEmail cannot be blank")
	@Email(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "Invail email Id")
	private String patientEmail;
	
	@NotNull(message = "PatientPhoneNumber cannot be null")
	@NotBlank(message = "PatientPhoneNumber cannot be blank")
	@Pattern(regexp = "^(?:\\+91|91|0)?[789]\\d{9}$", message = "Invalid Mobile number")
	private String patientPhoneNumber;
	
	@NotNull(message = "PatientGender cannot be null")
	private Gender patientGender;
	
	@NotNull(message = "PatientDOB cannot be null")
	private LocalDate patientDateOfBirth;
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}
	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}
	public Gender getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(Gender patientGender) {
		this.patientGender = patientGender;
	}
	public LocalDate getPatientDateOfBirth() {
		return patientDateOfBirth;
	}
	public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}
	
	

}
