package com.jsp.pharmassist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.pharmassist.responsedtos.MedicineResponse;
import com.jsp.pharmassist.service.MedicineService;
import com.jsp.pharmassist.utility.AppResponseBuilder;
import com.jsp.pharmassist.utility.ResponseStructure;
import com.jsp.pharmassist.utility.SimpleResponseStructure;

@RestController
public class MedicineController {
	
	private final AppResponseBuilder responseBuilder;
	private final MedicineService medicineService;

	public MedicineController(AppResponseBuilder responseBuilder, MedicineService medicineService) {
		super();
		this.responseBuilder = responseBuilder;
		this.medicineService = medicineService;
	}

	@PostMapping("pharmacies/{pharmacyId}/medicines")
	public ResponseEntity<SimpleResponseStructure> uploadMedicines(@RequestParam("medicine_list") MultipartFile file,@PathVariable String pharmacyId) {
		
		String message = medicineService.uploadMedicines(file,pharmacyId);
		return responseBuilder.success(HttpStatus.CREATED, message);	
	}
	
	@GetMapping("medicines/{name}/{ingredient}")
	public ResponseEntity<ResponseStructure<List<MedicineResponse>>> findMedicineByNameOrIngredient(@PathVariable String name, @PathVariable String ingredient) {
	
	List<MedicineResponse> medicines = medicineService.findMedicineByNameOrIngredient(name, ingredient);
	return responseBuilder.success(HttpStatus.FOUND,"Medicines Found", medicines);
}

}
