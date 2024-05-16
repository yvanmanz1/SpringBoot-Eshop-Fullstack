package com.example.ecommfullstack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommfullstack.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
