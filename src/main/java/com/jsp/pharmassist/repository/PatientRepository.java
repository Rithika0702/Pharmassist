package com.jsp.pharmassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient , String> {

}
