package com.uni.platform.service.category;

import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.dto.category.CreateCategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto getCategoryByName(String name);

    ResponseEntity<String> insertCategory(CreateCategoryDto createCategoryDto);

    ResponseEntity<String> updateCategoryById(Long id, CreateCategoryDto categoryDto);

    ResponseEntity<String> deleteById(Long id);
}
