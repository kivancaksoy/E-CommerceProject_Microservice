package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateProductRequest;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
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

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ProductDto createProduct(CreateProductRequest createProductRequest) {
        Category category = categoryService.findCategoryById(createProductRequest.getCategoryId());
        Product product = new Product(
                createProductRequest.getProductName(),
                createProductRequest.getDetails(),
                createProductRequest.getPrice(),
                category
        );

        Product createdProduct = productRepository.save(product);

        return new ProductDto(
                Objects.requireNonNull(createdProduct.getId()),
                createdProduct.getProductName(),
                createdProduct.getDetails(),
                createdProduct.getPrice(),
                new CategoryDto(
                        createdProduct.getId(),
                        createdProduct.getProductName()
                )
        );
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product ->
                        new ProductDto(
                                Objects.requireNonNull(product.getId()),
                                product.getProductName(),
                                product.getDetails(),
                                product.getPrice(),
                                new CategoryDto(
                                        Objects.requireNonNull(product.getCategory().getId()),
                                        product.getCategory().getCategoryName()
                                )
                        )).toList();
    }
}
