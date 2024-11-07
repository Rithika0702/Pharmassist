package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Medicine;
import com.jsp.pharmassist.requestdtos.MedicineRequest;
import com.jsp.pharmassist.responsedtos.MedicineResponse;

@Component
public class MedicineMapper {
	
	public Medicine mapToMedicine(MedicineRequest medicineRequest, Medicine medicine) {
		
		medicine.setName(medicineRequest.getName());
		medicine.setCategory(medicineRequest.getCategory());
		medicine.setDosageInMg(medicineRequest.getDosageInMg());
		medicine.setForm(medicineRequest.getForm());
		medicine.setIngredients(medicineRequest.getIngredients());
		medicine.setManufacturer(medicineRequest.getManufacturer());
		medicine.setPrice(medicineRequest.getPrice());
		medicine.setExpiryDate(medicineRequest.getExpiryDate());
		medicine.setStockQuantity(medicineRequest.getStockQuantity());
			
		return medicine;
	}
	
	public MedicineResponse mapToMedicineResponse(Medicine medicine) {
		
		MedicineResponse response = new MedicineResponse();
		
		response.setMedicineId(medicine.getMedicineId());
		response.setName(medicine.getName());
		response.setCategory(medicine.getCategory());
		response.setDosageInMg(medicine.getDosageInMg());
		response.setForm(medicine.getForm());
		response.setIngredients(medicine.getIngredients());
		response.setManufacturer(medicine.getManufacturer());
		response.setPrice(medicine.getPrice());
		response.setExpiryDate(medicine.getExpiryDate());
		response.setStockQuantity(medicine.getStockQuantity());
		
		return response;
	}

}
