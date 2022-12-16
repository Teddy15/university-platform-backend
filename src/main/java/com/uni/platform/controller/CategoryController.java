package com.uni.platform.controller;

import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.dto.category.CreateCategoryDto;
import com.uni.platform.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<CategoryDto> getAllCategories() {
        log.info("getAllCategories() called");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CategoryDto getCategoryByName(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> insertCategory(@Validated @RequestBody CreateCategoryDto createCategoryDto) {
        log.info("insertCategory() called");
        return categoryService.insertCategory(createCategoryDto);
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateCategoryByName(@PathVariable Long categoryId, @Validated @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategoryById(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategoryByName(@PathVariable Long categoryId) {
        return categoryService.deleteById(categoryId);
    }
}
