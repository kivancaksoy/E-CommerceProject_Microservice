package com.kivanc.ecommerce.productservice;

import com.kivanc.ecommerce.productservice.dto.*;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.model.Product;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import org.assertj.core.util.Arrays;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestSupport {
    public Category generateCategory() {
        return new Category("category-name");
    }

    public Category generateCategory(String id) {
        return new Category(id, "category-name");
    }

    public List<Category> generateCategoryList() {
        Category category1 = new Category("category-id-1", "category-name-1");
        Category category2 = new Category("category-id-2", "category-name-2");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        return categoryList;
    }

    public CategoryDto generateCategoryDto() {
        return new CategoryDto("category-id", "category-name");
    }

    public List<CategoryDto> generateCategoryDtoList() {
        CategoryDto categoryDto1 = new CategoryDto("category-id-1", "category-name-1");
        CategoryDto categoryDto2 = new CategoryDto("category-id-2", "category-name-2");
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryDto1);
        categoryDtoList.add(categoryDto2);

        return categoryDtoList;
    }

    public CreateCategoryRequest generateCreateCategoryRequest() {
        return new CreateCategoryRequest("category-name");
    }

    public Product generateProduct() {
        return new Product(
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                generateCategory("category-id"));
    }

    public Product generateProduct(String id) {
        return new Product(
                id,
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                generateCategory("category-id"));
    }

    public List<Product> generateProductList() {
        Product product1 = generateProduct("product-id-1");
        Product product2 = generateProduct("product-id-2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        return productList;
    }

    public ProductDto generateProductDto() {
        return new ProductDto(
                "product-id",
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                generateCategoryDto());
    }

    public List<ProductDto> generateProductDtoList() {
        ProductDto productDto1 = new ProductDto(
                "product-id-1",
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                generateCategoryDto());

        ProductDto productDto2 = new ProductDto(
                "product-id-2",
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                generateCategoryDto());

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto1);
        productDtoList.add(productDto2);

        return productDtoList;
    }

    public CreateProductRequest generateCreateProductRequest() {
        return new CreateProductRequest(
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                "supplier-id",
                "category-id");
    }

    public ProductImage generateProductImage() {
        return new ProductImage("randomFileName.jpg", "folderPathTest", generateProduct("product-id"));
    }

    public ProductImageDto generateProductImageDto() {
        return new ProductImageDto("1", "randomFileName.jpg", "folderPathTest", "product-id");
    }

    public MultipartFile generateMultipartFile() {
        return new MockMultipartFile("image", "testImage.jpg", "image/jpeg", new byte[1]);
    }
}
