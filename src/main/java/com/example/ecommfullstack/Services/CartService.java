package com.example.ecommfullstack.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Cart;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Models.User;
import com.example.ecommfullstack.Repository.CartRepository;
import com.example.ecommfullstack.Repository.ProductRepository;
import com.example.ecommfullstack.Security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsInCartByUserId(String token) {
        Long userId = extractUserIdFromToken(token); 
        System.out.println(userId);// Ensure this method correctly extracts user ID
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<Product> products = cart.getProducts();
            // Fetch and enrich product information
            products.forEach(product -> {
                Product fullProductInfo = productService.getProductById(product.getId());
                product.setName(fullProductInfo.getName());
                product.setPrice(fullProductInfo.getPrice());
                product.setDescription(fullProductInfo.getDescription());
                
            });
            return products;
        } else {
            return Collections.emptyList(); // Return an empty list if cart is not found
        }
    }


    public void removeItemFromCart(Long productId, String token) throws Exception {
        // Logic to get the user from the token
        Long userId = extractUserIdFromToken(token);
        User user = userService.getUserById(userId);
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (!cartOptional.isPresent()) {
            throw new Exception("Cart not found for user");
        }

        Cart cart = cartOptional.get();
        Optional<Product> productOptional = productRepository.findById(productId);

        if (!productOptional.isPresent()) {
            throw new Exception("Product not found");
        }

        Product product = productOptional.get();
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }
    
    
    @Transactional
    public void addToCart(Long productId, String token) {
        // 1. Extract the user ID from the JWT token
        Long userId = extractUserIdFromToken(token);

        // 2. Retrieve the user entity from the database using the user ID
        User user = userService.getUserById(userId);

        // 3. Retrieve the existing cart for the user
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return newCart;
        });

        // 4. Retrieve the product entity from the database using the product ID
        Product product = productService.getProductById(productId);

        // 5. Add the product to the cart's list of products
        List<Product> products = cart.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        cart.setProducts(products);

        // 6. Save the cart entity to persist the changes to the database
        cartRepository.save(cart);
    }

    
    private Long extractUserIdFromToken(String token) {
        try {
            return jwtTokenProvider.getUserIdFromJWT(token);
        } catch (Exception ex) {
            ex.printStackTrace(); // For demonstration, you can log the exception
            return null; // Return null or any default value based on your requirement
        }
    }

}

