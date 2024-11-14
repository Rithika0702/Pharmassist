package com.jsp.pharmassist.entity;

import java.time.LocalDate;

import com.jsp.pharmassist.config.GenerateCustomId;
import com.jsp.pharmassist.enums.Form;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Medicine {
	
	@Id
	@GenerateCustomId
	private String medicineId;
	
	@NotNull(message=" Name cannot be null")
	@NotBlank(message = "Name cannot be blank")
	@Pattern(regexp = "^[A-Za-z0-9\\s\\-]{2,50}$" , message ="Invalid name")
	private String name;
	

	@NotNull(message ="Category cannot be null")
	@NotBlank(message ="Category cannot be blank")
	@Pattern(regexp ="^[A-Za-z\\s\\-\\/]{2,50}$",message = "Invalid category")
	private String category;
	
	@NotNull(message ="Ingredients cannot be null")
	@NotBlank(message ="Ingredients cannot be blank")
	@Pattern(regexp ="^[A-Za-z0-9\\s\\-]{1,200}$",message = "Invalid ingredients")
	private String ingredients;
	
	@Min(value = 1)
	private int dosageInMg;
	
	@NotNull(message="form cannot be null")
	@Enumerated(EnumType.STRING)
	private Form form;
	
	@NotNull(message ="Manufacturer cannot be null")
	@NotBlank(message ="Manufacturer cannot be blank")
	@Pattern(regexp ="^[A-Za-z\\s\\.,]{1,100}$",message = "Invalid manufacturer")
	private String manufacturer;
	
	@Min(value = 0)
	private int stockQuantity;
	
	private LocalDate expiryDate;
	
	@Min(value = 0)
	private double price;
	
	@ManyToOne
	private Pharmacy pharmacy;
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public int getDosageInMg() {
		return dosageInMg;
	}
	public void setDosageInMg(int dosageInMg) {
		this.dosageInMg = dosageInMg;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	

}
