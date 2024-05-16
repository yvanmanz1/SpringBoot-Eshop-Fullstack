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
    public ResponseEntity<?> getProductsInCartByUserId(@RequestHeader(name = "Authorization") String token) {
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

//    @PostMapping("/add")
//    public ResponseEntity<String> addProductToCart(@RequestBody AddToCartRequest addToCartRequest, HttpServletRequest request) {
//        // Get the JWT token from the request headers
//        String token = request.getHeader("Authorization").substring(7); // Remove "Bearer " prefix
//        
//        // Extract user ID from JWT token
//        Long userId = tokenProvider.getUserIdFromJWT(token);
//
//        // Add the product to the user's cart
//        cartService.addProductToCart(userId, addToCartRequest.getProductId());
//
//        return ResponseEntity.ok("Product added to cart successfully");
//    
//    
//    }
    
    

//    @GetMapping("/{userId}")
//    public Cart getCartByUserId(@PathVariable Long userId) {
//        return cartService.getCartByUserId(userId);
//    }

//    

//    @DeleteMapping("/{userId}/remove/{productId}")
//    public Cart removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
//        return cartService.removeFromCart(userId, productId);
//    }

    // Add other endpoints for updating and managing the cart as needed
}

