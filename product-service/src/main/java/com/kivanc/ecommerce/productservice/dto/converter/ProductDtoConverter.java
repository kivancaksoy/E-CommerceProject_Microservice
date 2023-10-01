package com.kivanc.ecommerce.productservice.dto.converter;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
import com.kivanc.ecommerce.productservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductDtoConverter {
    private final CategoryDtoConverter categoryDtoConverter;

    public ProductDtoConverter(CategoryDtoConverter categoryDtoConverter) {
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public ProductDto convertToProductDto(Product product) {
        return new ProductDto(
                Objects.requireNonNull(product.getId()),
                product.getProductName(),
                product.getDetails(),
                product.getPrice(),
                product.getUnitsInStock(),
                categoryDtoConverter.convertToCategoryDto(product.getCategory())
        );
    }
}
