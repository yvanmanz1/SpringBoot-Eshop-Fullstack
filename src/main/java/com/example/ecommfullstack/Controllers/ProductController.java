package com.example.ecommfullstack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Models.ProductRequest;
import com.example.ecommfullstack.Repository.CategoryRepository;
import com.example.ecommfullstack.Repository.ProductRepository;
import com.example.ecommfullstack.Services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.IOException;
import java.util.List;
import java.util.Base64;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
 //   @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductRequest productRequest) {
        try {
            String pictureData = productRequest.getPicture();
            // Check if pictureData is null or empty
            if (pictureData != null) {
                // Handle case when image is not uploaded
                // You can choose to set a default image, or leave it blank depending on your requirement
                // For now, let's assume we set it to null
                productRequest.setPicture(pictureData);
            }

            // Your existing code to create the product
            ProductService productService = new ProductService(productRepository, null);
            Product createdProduct = productService.createProductFromJson(productRequest);

            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
//    private boolean isValidBase64(byte[] decodedBytes) {
//        try {
//            Base64.getEncoder().encodeToString(decodedBytes); // If the encoding succeeds, it's valid Base64
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }





    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        product.setPicture(productDetails.getPicture());
        product.setCategoryId(productDetails.getCategoryId());
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    
//    @GetMapping("/product/image/{id}")
//    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
//        Product product = ProductService.getProductById(id);
//        byte[] imageData = product.getPicture();
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG) // Assuming the image format is JPEG
//                .body(imageData);
//    }
}
