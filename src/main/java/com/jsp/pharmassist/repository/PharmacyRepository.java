package com.jsp.pharmassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, String>{

}
