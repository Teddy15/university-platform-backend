package com.uni.platform.mapper;

import com.uni.platform.dto.category.CreateCategoryDto;
import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryEntityToCategoryDto(Category src);

    List<CategoryDto> categoryEntityToCategoryDto(List<Category> src);

    Category categoryDtoToCategoryEntity(CategoryDto src);

    Category createCategoryDtoToCategoryEntity(CreateCategoryDto src);
}