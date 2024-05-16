package com.example.ecommfullstack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Order;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Repository.OrderRepository;
import com.example.ecommfullstack.Services.OrderService;
import com.example.ecommfullstack.Services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
    	List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
    
 //   @PreAuthorize("hasRole('ADMIN')") 
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setDetails(orderDetails.getDetails());
        order.setQuantity(orderDetails.getQuantity());
        order.setProduct(orderDetails.getProduct());
        order.setUser(orderDetails.getUser());
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}

