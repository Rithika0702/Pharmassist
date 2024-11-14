package com.jsp.pharmassist.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.entity.Pharmacy;

public interface AdminRepository extends JpaRepository<Admin, String>{
	

	//HQL query to select the pharmacy associated with the admin

	@Query("SELECT a.pharmacy FROM Admin a WHERE a.id= :adminId")
	Pharmacy findPharmacyByAdminId(@Param("adminId") String adminId);
	
	Optional<Admin> findByAdminEmail(String adminEmail);

}
