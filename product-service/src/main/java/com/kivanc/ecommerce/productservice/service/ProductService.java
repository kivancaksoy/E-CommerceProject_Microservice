package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CreateProductRequest;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
import com.kivanc.ecommerce.productservice.dto.UpdateProductStockRequest;
import com.kivanc.ecommerce.productservice.dto.converter.ProductDtoConverter;
import com.kivanc.ecommerce.productservice.exception.ProductNotFoundException;
import com.kivanc.ecommerce.productservice.model.Product;
import com.kivanc.ecommerce.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
                createProductRequest.getUnitsInStock(),
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

    public ProductDto getProductById(String id) {
        return productDtoConverter.convertToProductDto(findProductById(id));
    }

    protected Product findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product could not be found by id: " + productId));
    }

    public void updateStock(List<UpdateProductStockRequest> updateProductStockRequestList) {
        List<Product> productList = new ArrayList<>();
        for (UpdateProductStockRequest updateProductStockRequest : updateProductStockRequestList) {
            Product product = findProductById(updateProductStockRequest.getId());

            if (product.getUnitsInStock() - updateProductStockRequest.getQuantity() < 0) {
                throw new RuntimeException("Stock can not be negative.");
            }

            product.setUnitsInStock(product.getUnitsInStock() - updateProductStockRequest.getQuantity());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }
}
