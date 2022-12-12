package com.uni.platform.service;

import com.uni.platform.repository.CategoryRepository;
import com.uni.platform.dto.category.CreateCategoryDto;
import com.uni.platform.mapper.CategoryMapper;
import com.uni.platform.dto.category.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    private static final String CREATE_SUCCESS = "Successfully created a category!";
    private static final String UPDATE_SUCCESS = "Successfully updated a category!";
    private static final String DELETE_SUCCESS = "Successfully deleted a category!";

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.categoryEntityToCategoryDto(categoryRepository.findAll());
    }

    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.categoryEntityToCategoryDto(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No category found with id: " + id)));
    }

    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryEntityToCategoryDto(
                categoryRepository.findByName(name)
                        .orElseThrow(() -> new NoSuchElementException("No category found with name: " + name)));
    }

    public ResponseEntity<String> insertCategory(CreateCategoryDto createCategoryDto) {
        categoryRepository.save(categoryMapper.createCategoryDtoToCategoryEntity(createCategoryDto));
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> updateCategoryById(Long id, CategoryDto categoryDto) {
        getCategoryById(id);
        categoryRepository.save(categoryMapper.categoryDtoToCategoryEntity(categoryDto));
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Long id) {
        getCategoryById(id);

        categoryRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}