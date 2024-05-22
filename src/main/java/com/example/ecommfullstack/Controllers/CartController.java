package com.example.ecommfullstack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Cart;
import org.springframework.http.HttpStatus;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Security.JwtTokenProvider;
import com.example.ecommfullstack.Services.CartService;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;
    private final JwtTokenProvider tokenProvider;

    public CartController(CartService cartService, JwtTokenProvider tokenProvider) {
        this.cartService = cartService;
        this.tokenProvider = tokenProvider;
    }
    
    
    @GetMapping("/user")
    public ResponseEntity<List<Product>> getProductsInCartByUserId(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Received token: " + token);  // Debug log
        List<Product> productsInCart = cartService.getProductsInCartByUserId(token);
        if (!productsInCart.isEmpty()) {
            return ResponseEntity.ok(productsInCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
    
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(name = "Authorization") String token) {
    	
    	System.out.println(token);
        try {
            cartService.addToCart(productId, token);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product to cart.");
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        try {
            cartService.removeItemFromCart(productId, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing item from cart: " + e.getMessage());
        }
    }

    // Add other endpoints for updating and managing the cart as needed
}

