package com.example.ecommfullstack.Models;

import java.util.List;

public class OrderRequest {
    private List<ProductRequest> products;
    private double totalAmount;
    
    
	public List<ProductRequest> getProducts() {
		return products;
	}
	public void setProducts(List<ProductRequest> products) {
		this.products = products;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
