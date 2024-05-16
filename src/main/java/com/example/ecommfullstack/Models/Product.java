package com.example.ecommfullstack.Models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private Integer qty;
    private String description;
    
    private String picture;

    private Long categoryId;

    
    
	public Product() {
		
	}

	public Product(Long id, String name, double price, String description, 	String picture, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.picture = picture;
		this.categoryId = categoryId;
	}

	public Product(Long id, String name, double price, Integer qty, String description, String picture,
			Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.description = description;
		this.picture = picture;
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
    
    
    
}
