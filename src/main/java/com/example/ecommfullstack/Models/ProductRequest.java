package com.example.ecommfullstack.Models;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
    private String name;
    private double price;
    private Integer qty;
    private String description;
    private String picture; // Change the type to byte[] instead of MultipartFile
    private Long categoryId;

    // Getters and setters


    
    // New field for category ID
	public ProductRequest() {
		
	}



	public ProductRequest(String name, double price, Integer qty, String description, String picture,
			Long categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.description = description;
		this.picture = picture;
		this.categoryId = categoryId;
	}




	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}




	





	
   
}


