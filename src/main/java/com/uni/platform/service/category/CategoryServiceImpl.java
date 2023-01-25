package com.uni.platform.service.category;

import com.uni.platform.entity.Category;
import com.uni.platform.repository.CategoryRepository;
import com.uni.platform.dto.category.CreateCategoryDto;
import com.uni.platform.mapper.CategoryMapper;
import com.uni.platform.dto.category.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService{
    private static final String CREATE_SUCCESS = "Successfully created a category!";
    private static final String UPDATE_SUCCESS = "Successfully updated a category!";
    private static final String DELETE_SUCCESS = "Successfully deleted a category!";

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.categoryEntityToCategoryDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.categoryEntityToCategoryDto(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No category found with id: " + id)));
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryEntityToCategoryDto(
                categoryRepository.findByName(name)
                        .orElseThrow(() -> new NoSuchElementException("No category found with name: " + name)));
    }

    @Override
    @Transactional
    public ResponseEntity<String> insertCategory(CreateCategoryDto createCategoryDto) {
        Category category = categoryMapper.createCategoryDtoToCategoryEntity(createCategoryDto);
        category.setCreatedAt(LocalDateTime.now());
        category.setLastUpdatedAt(LocalDateTime.now());

        categoryRepository.save(category);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateCategoryById(Long id, CreateCategoryDto categoryDto) {
        CategoryDto category = getCategoryById(id);

        category.setName(categoryDto.getName());

        categoryRepository.save(categoryMapper.categoryDtoToCategoryEntity(category));
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        getCategoryById(id);

        categoryRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
