package com.example.ecommfullstack.Services;

import org.springframework.stereotype.Service;
import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Category;
import com.example.ecommfullstack.Models.Product;
import com.example.ecommfullstack.Models.ProductRequest;
import com.example.ecommfullstack.Repository.CategoryRepository;
import com.example.ecommfullstack.Repository.ProductRepository;

import java.util.List;
import java.io.IOException;

@Service
public class ProductService {

	private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    


	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Updated method to handle JSON data
    public Product createProductFromJson(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setPicture(productRequest.getPicture()); // Set picture data directly
        product.setCategoryId(productRequest.getCategoryId()); // Set categoryId directly

        return productRepository.save(product);
    }



    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPicture(updatedProduct.getPicture());
        existingProduct.setCategoryId(updatedProduct.getCategoryId());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
