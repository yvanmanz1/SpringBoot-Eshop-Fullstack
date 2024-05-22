package com.example.ecommfullstack.Models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String details;
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String status;
    
    private double totalamount;
    
    

	public Order() {
		
	}

	public Order(Long id, String details, int quantity, Product product, User user, String status, double totalamount) {
		super();
		this.id = id;
		this.details = details;
		this.quantity = quantity;
		
		this.user = user;
		this.status = status;
		this.totalamount = totalamount;
	}
	
	

	public Order(Long id, String details, int quantity, List<Product> products, User user, String status,
			double totalamount) {
		super();
		this.id = id;
		this.details = details;
		this.quantity = quantity;
		this.products = products;
		this.user = user;
		this.status = status;
		this.totalamount = totalamount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
    
    
}
