package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateProductRequest;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductDtoConverter;
import com.kivanc.ecommerce.productservice.exception.ProductNotFoundException;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.model.Product;
import com.kivanc.ecommerce.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProductServiceTest {
    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductDtoConverter productDtoConverter;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        categoryService = Mockito.mock(CategoryService.class);
        productDtoConverter = Mockito.mock(ProductDtoConverter.class);

        productService = new ProductService(productRepository, categoryService, productDtoConverter);
    }

    @Test
    public void testGetProductById_whenProductIdDoesNotExist_shouldThrowProductNotFoundException() {
        String productId = "product-id";

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(productId));

        Mockito.verifyNoInteractions(productDtoConverter);
    }

    @Test
    public void testGetProductById_whenProductIdExists_shouldReturnProductDto() {
        String productId = "product-id";
        Category category = new Category("category-id", "category-name");
        CategoryDto categoryDto = new CategoryDto("category-id", "category-name");

        Product product = new Product(
                productId,
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                category);

        ProductDto productDto = new ProductDto(
                productId,
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                categoryDto);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(productDtoConverter.convertToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.getProductById(productId);

        assertEquals(result, productDto);

        Mockito.verify(productRepository).findById(productId);
        Mockito.verify(productDtoConverter).convertToProductDto(product);
    }

    @Test
    public void testGetAllProducts_shouldReturnProductDtoList() {
        Category category = new Category("category-id", "category-name");
        CategoryDto categoryDto = new CategoryDto("category-id", "category-name");

        Product product1 = new Product(
                "product-id-1",
                "product-name-1",
                "product-details-1",
                new BigDecimal(10),
                5,
                category);

        Product product2 = new Product(
                "product-id-2",
                "product-name-2",
                "product-details-2",
                new BigDecimal(100),
                51,
                category);

        ProductDto productDto1 = new ProductDto(
                "product-id-1",
                "product-name-1",
                "product-details-1",
                new BigDecimal(10),
                5,
                categoryDto);

        ProductDto productDto2 = new ProductDto(
                "product-id-2",
                "product-name-2",
                "product-details-2",
                new BigDecimal(100),
                51,
                categoryDto);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto1);
        productDtoList.add(productDto2);

        Mockito.when(productRepository.findAll()).thenReturn(productList);
        //TODO Check out the following two approaches for productDtoConverter
        //Mockito.when(productDtoConverter.convertToProductDto(product1)).thenReturn(productDto1);
        //Mockito.when(productDtoConverter.convertToProductDto(product2)).thenReturn(productDto2);

        Mockito.when(productDtoConverter.convertToProductDto(any())).thenAnswer(
                invocation -> {
                    Product product = invocation.getArgument(0);
                    return new ProductDto(Objects.requireNonNull(
                            product.getId()),
                            product.getProductName(),
                            product.getDetails(),
                            product.getPrice(),
                            product.getUnitsInStock(),
                            new CategoryDto(
                                    Objects.requireNonNull(product.getCategory().getId()),
                                    product.getCategory().getCategoryName()
                            ));
                });

        List<ProductDto> result = productService.getAllProducts();

        assertEquals(result, productDtoList);

        Mockito.verify(productRepository).findAll();
        Mockito.verify(productDtoConverter).convertToProductDto(product1);
        Mockito.verify(productDtoConverter).convertToProductDto(product2);
    }

    @Test
    public void testCreateProduct_shouldReturnProductDto() {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "12345"
        );

        Category category = new Category("12345", "category-name");
        CategoryDto categoryDto = new CategoryDto("12345", "category-name");

        Product product = new Product(
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                category);

        ProductDto productDto = new ProductDto(
                "product-name",
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                categoryDto);

        Mockito.when(categoryService.findCategoryById("12345")).thenReturn(category);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Mockito.when(productDtoConverter.convertToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.createProduct(createProductRequest);

        assertNotNull(result);
        assertEquals(result, productDto);

        Mockito.verify(productRepository).save(product);
        Mockito.verify(categoryService).findCategoryById("12345");
        Mockito.verify(productDtoConverter).convertToProductDto(product);
    }
}