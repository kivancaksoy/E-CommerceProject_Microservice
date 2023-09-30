package com.kivanc.ecommerce.productservice.service;


import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateCategoryRequest;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category(createCategoryRequest.getCategoryName());
        Category createdCategory = categoryRepository.save(category);
        return new CategoryDto(
                Objects.requireNonNull(createdCategory.getId()),
                category.getCategoryName()
        );
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category ->
                        new CategoryDto(
                                Objects.requireNonNull(category.getId()),
                                category.getCategoryName())
                ).toList();
    }

    protected Category findCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category could not be found by id: " + categoryId));

    }
}