package com.jsp.pharmassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.entity.Pharmacy;

public interface PatientRepository extends JpaRepository<Patient , String> {
	
	List<Patient> findByPharmacy(Pharmacy pharmacy);

}
