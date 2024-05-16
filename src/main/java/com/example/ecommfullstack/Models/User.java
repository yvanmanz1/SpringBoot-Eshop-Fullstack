package com.example.ecommfullstack.Models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole role;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Cart cart;

	
    
    
	public User() {
		
	}
	public User(Long id, String email, String name, String password, AppUserRole role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	
	
	public User(Long id, String email, String name, String password, AppUserRole role, Cart cart) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
		this.cart = cart;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AppUserRole getRole() {
		return role;
	}
	public void setRole(AppUserRole role) {
		this.role = role;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
    
    

}
