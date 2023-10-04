package com.kivanc.ecommerce.productservice.dto.converter;

import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductImageDtoConverter {

    public ProductImageDto convertToProductImageDto(ProductImage productImage) {
        return new ProductImageDto(
                Objects.requireNonNull(productImage.getId()),
                productImage.getImageName(),
                productImage.getImagePath(),
                Objects.requireNonNull(productImage.getProduct().getId())
        );
    }
}
