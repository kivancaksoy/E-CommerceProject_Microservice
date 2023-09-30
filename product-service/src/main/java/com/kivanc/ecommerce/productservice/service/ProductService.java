package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateProductRequest;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductDtoConverter;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.model.Product;
import com.kivanc.ecommerce.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductDtoConverter productDtoConverter;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductDtoConverter productDtoConverter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productDtoConverter = productDtoConverter;
    }

    public ProductDto createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product(
                createProductRequest.getProductName(),
                createProductRequest.getDetails(),
                createProductRequest.getPrice(),
                categoryService.findCategoryById(createProductRequest.getCategoryId())
        );

        return productDtoConverter.convertToProductDto(productRepository.save(product));
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(productDtoConverter::convertToProductDto)
                .toList();
    }
}
