package com.uni.platform.service;

import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.dto.category.CreateCategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();

    public CategoryDto getCategoryById(Long id);

    public CategoryDto getCategoryByName(String name);

    public ResponseEntity<String> insertCategory(CreateCategoryDto createCategoryDto);

    public ResponseEntity<String> updateCategoryById(Long id, CategoryDto categoryDto);

    public ResponseEntity<String> deleteById(Long id);
}
