package com.example.ecommfullstack.Services;

import org.springframework.stereotype.Service;

import com.example.ecommfullstack.Exceptions.ResourceNotFoundException;
import com.example.ecommfullstack.Models.Category;
import com.example.ecommfullstack.Repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }
}

