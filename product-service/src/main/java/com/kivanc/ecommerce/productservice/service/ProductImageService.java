package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CreateProductImageRequest;
import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductImageDtoConverter;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import com.kivanc.ecommerce.productservice.repository.ProductImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageDtoConverter productImageDtoConverter;
    private final ProductService productService;

    public ProductImageService(ProductImageRepository productImageRepository, ProductImageDtoConverter productImageDtoConverter, ProductService productService) {
        this.productImageRepository = productImageRepository;
        this.productImageDtoConverter = productImageDtoConverter;
        this.productService = productService;
    }

    public ProductImageDto createProductImage(CreateProductImageRequest createProductImageRequest) {
        ProductImage productImage = new ProductImage(
                createProductImageRequest.getImageName(),
                createProductImageRequest.getImagePath(),
                productService.findProductById(createProductImageRequest.getProductId())
        );

        return productImageDtoConverter.convertToProductImageDto(productImageRepository.save(productImage));
    }
}
