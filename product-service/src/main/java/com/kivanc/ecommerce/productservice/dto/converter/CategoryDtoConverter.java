package com.kivanc.ecommerce.productservice.dto.converter;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.model.Category;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryDtoConverter {
    public CategoryDto convertToCategoryDto(Category category) {
        return new CategoryDto(
                Objects.requireNonNull(category.getId()),
                category.getCategoryName()
        );
    }
}
