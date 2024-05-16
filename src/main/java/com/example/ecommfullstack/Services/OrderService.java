package com.example.ecommfullstack.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Order;
import com.example.ecommfullstack.Models.OrderStatus;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Models.User;
import com.example.ecommfullstack.Repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

//    public List<Order> getOrderHistory(Long userId) {
//        User user = userService.getUserById(userId);
//        return orderRepository.findByUser(user);
//    }
    
   
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
        
    }

    public Order placeOrder(Long userId, Product product) {
        User user = userService.getUserById(userId);
        // Create order logic goes here
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setStatus(OrderStatus.PENDING.toString()); // Map enum value to string representation
        return orderRepository.save(order);
    }



    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status.toString());
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
}

