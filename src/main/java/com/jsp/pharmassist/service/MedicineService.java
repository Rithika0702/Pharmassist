package com.jsp.pharmassist.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.pharmassist.entity.Medicine;
import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.enums.Form;
import com.jsp.pharmassist.exception.InvalidDataException;
import com.jsp.pharmassist.exception.InvalidDateFormatException;
import com.jsp.pharmassist.exception.InvalidFileFormatException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.repository.MedicineRepository;
import com.jsp.pharmassist.repository.PharmacyRepository;

import jakarta.validation.Valid;

@Service
public class MedicineService {
	
	private final PharmacyRepository pharmacyRepository;
	private final MedicineRepository medicineRepository;

	public MedicineService(PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository) {
		super();
		this.pharmacyRepository = pharmacyRepository;
		this.medicineRepository = medicineRepository;
	}

   //@Transactional
	public String uploadMedicines(MultipartFile file,String pharmacyId) {
		
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
				.orElseThrow(()-> new PharmacyNotFoundByIdException("Failed to find pharmacy with Id"+pharmacyId));
		
		try(XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
					
			for(Sheet sheet : workbook) {
				for(Row row : sheet) {
					if(row.getRowNum()!=0)
					{						
						Medicine medicine = getMedicine(row);
						
						medicine.setPharmacy(pharmacy);
						
						saveMedicine(medicine);
						
						pharmacy.getMedicines().add(medicine);										
					}
				}
			}
			
		}catch ( NotOfficeXmlFileException | IOException e ) {
             throw new InvalidFileFormatException("Invalid file format");
		}
		
		return "Medicines Added";
	}
	
	
	public Medicine getMedicine(Row row) {
	
		    Medicine medicine = new Medicine();
		    
            try {		
		    medicine.setName(row.getCell(0).getStringCellValue());
		    medicine.setCategory(row.getCell(1).getStringCellValue());
		    medicine.setDosageInMg((int) row.getCell(2).getNumericCellValue());
		    medicine.setForm(Form.valueOf(row.getCell(3).getStringCellValue().toUpperCase()));
		    medicine.setIngredients(row.getCell(4).getStringCellValue());
		    medicine.setManufacturer(row.getCell(5).getStringCellValue());
		    medicine.setPrice(row.getCell(6).getNumericCellValue());
		
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
            medicine.setExpiryDate(LocalDate.parse(row.getCell(7).getStringCellValue(), formatter));
            
		    medicine.setStockQuantity((int) row.getCell(8).getNumericCellValue());
		
            } catch (IllegalStateException | NumberFormatException e) 
    		{
			      throw new InvalidDataException("Failed to add data in row :"+row.getRowNum());
			      
            } catch (DateTimeParseException e) {
			       throw new InvalidDateFormatException("Invalid date format in row " + row.getRowNum());
            }
            
            return medicine;
	}
	
	public void saveMedicine(@Valid Medicine medicine) {
		
		medicineRepository.save(medicine); 		
	}



}
