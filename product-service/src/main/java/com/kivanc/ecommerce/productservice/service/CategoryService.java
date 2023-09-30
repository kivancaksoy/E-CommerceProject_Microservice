package com.kivanc.ecommerce.productservice.service;


import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateCategoryRequest;
import com.kivanc.ecommerce.productservice.dto.converter.CategoryDtoConverter;
import com.kivanc.ecommerce.productservice.exception.CategoryNotFoundException;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public CategoryDto createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category(createCategoryRequest.getCategoryName());
        return categoryDtoConverter.convertToCategoryDto(categoryRepository.save(category));
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(categoryDtoConverter::convertToCategoryDto)
                .toList();
    }

    protected Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category could not be found by id: " + id));

    }

    public CategoryDto getCategoryById(String id) {
        return categoryDtoConverter.convertToCategoryDto(findCategoryById(id));
    }
}