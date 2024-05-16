package com.example.ecommfullstack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommfullstack.Models.Order;
import com.example.ecommfullstack.Models.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}


