package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateCategoryRequest;
import com.kivanc.ecommerce.productservice.dto.converter.CategoryDtoConverter;
import com.kivanc.ecommerce.productservice.exception.CategoryNotFoundException;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CategoryServiceTest {
    private CategoryRepository categoryRepository;
    private CategoryDtoConverter categoryDtoConverter;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryDtoConverter = Mockito.mock(CategoryDtoConverter.class);

        categoryService = new CategoryService(categoryRepository, categoryDtoConverter);
    }

    @Test
    public void testGetCategoryById_whenCategoryIdDoesNotExist_shouldThrowCategoryNotFoundException() {
        String categoryId = "category-id";

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getCategoryById(categoryId));

        Mockito.verifyNoInteractions(categoryDtoConverter);
    }

    @Test
    public void testGetCategoryById_whenCategoryIdExists_shouldReturnCategoryDto() {
        String categoryId = "category-id";
        Category category = new Category(categoryId, "category-name");
        CategoryDto categoryDto = new CategoryDto(categoryId, "category-name");

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Mockito.when(categoryDtoConverter.convertToCategoryDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.getCategoryById(categoryId);

        assertEquals(result, categoryDto);

        Mockito.verify(categoryRepository).findById(categoryId);
        Mockito.verify(categoryDtoConverter).convertToCategoryDto(category);
    }

    @Test
    public void testGetAllCategories_shouldReturnCategoryDtoList() {
        Category category1 = new Category("category-id-1", "category-name-1");
        Category category2 = new Category("category-id-2", "category-name-2");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        CategoryDto categoryDto1 = new CategoryDto("category-id-1", "category-name-1");
        CategoryDto categoryDto2 = new CategoryDto("category-id-2", "category-name-2");
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryDto1);
        categoryDtoList.add(categoryDto2);

        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        Mockito.when(categoryDtoConverter.convertToCategoryDto(any())).thenAnswer(
                invocation -> {
                    Category category = invocation.getArgument(0);
                    return new CategoryDto(Objects.requireNonNull(category.getId()), category.getCategoryName());
                });

        List<CategoryDto> result = categoryService.getAllCategories();

        assertEquals(result, categoryDtoList);

        Mockito.verify(categoryRepository).findAll();
        Mockito.verify(categoryDtoConverter).convertToCategoryDto(category1);
        Mockito.verify(categoryDtoConverter).convertToCategoryDto(category2);

    }

    @Test
    public void testCreateCategory_shouldReturnCategoryDto() {
        Category category = new Category("category-name-1");
        CategoryDto categoryDto = new CategoryDto("category-id-1", "category-name-1");
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest("category-name-1");

        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Mockito.when(categoryDtoConverter.convertToCategoryDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.createCategory(createCategoryRequest);

        assertEquals(result, categoryDto);

        Mockito.verify(categoryRepository).save(category);
        Mockito.verify(categoryDtoConverter).convertToCategoryDto(category);

    }
}