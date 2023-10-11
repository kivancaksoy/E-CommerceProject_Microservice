package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductImageDtoConverter;
import com.kivanc.ecommerce.productservice.helper.FileNameHelper;
import com.kivanc.ecommerce.productservice.helper.LocalStorageService;
import com.kivanc.ecommerce.productservice.model.Category;
import com.kivanc.ecommerce.productservice.model.Product;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import com.kivanc.ecommerce.productservice.repository.ProductImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductImageServiceTest {
    private ProductImageRepository productImageRepository;
    private ProductImageDtoConverter productImageDtoConverter;
    private ProductService productService;
    private LocalStorageService localStorageService;

    private ProductImageService productImageService;

    @BeforeEach
    void setUp() {
        productImageRepository = mock(ProductImageRepository.class);
        productImageDtoConverter = mock(ProductImageDtoConverter.class);
        productService = mock(ProductService.class);
        localStorageService = mock(LocalStorageService.class);

        productImageService = new ProductImageService(productImageRepository, productImageDtoConverter, productService, localStorageService);
        ReflectionTestUtils.setField(productImageService, "folderPath", "deneme");

    }

    @Test
    public void uploadProductImage_withoutIOException_shouldReturnProductImageDto() throws IOException {
        String productId = "123";
        String folderPath = "deneme";
        String originalFileName = "testImage.jpg";
        MultipartFile imageFile = new MockMultipartFile("image", originalFileName, "image/jpeg", new byte[1]);
        String newFileName = "randomFileName.jpg";
        Category category = new Category("12345", "category-name");
        Product product = new Product(
                productId,
                "product-name",
                "product-details",
                new BigDecimal(10),
                5,
                category);

        ProductImage productImage = new ProductImage(newFileName, folderPath, product);
        ProductImageDto productImageDto = new ProductImageDto("1", newFileName, folderPath, productId);

        // mock static method
        MockedStatic<FileNameHelper> mockedStatic = mockStatic(FileNameHelper.class);
        mockedStatic.when(() -> FileNameHelper.generateRandomFileName(originalFileName)).thenReturn(newFileName);

        when(productService.findProductById(productId)).thenReturn(product);
        when(productImageRepository.save(productImage)).thenReturn(productImage);
        when(productImageDtoConverter.convertToProductImageDto(productImage)).thenReturn(productImageDto);

        ProductImageDto result = productImageService.uploadProductImage(imageFile, productId);

        assertNotNull(result);
        assertEquals(result, productImageDto);

        verify(localStorageService, times(1)).uploadImage(eq(imageFile), eq(folderPath), eq(newFileName));
        verify(productService, times(1)).findProductById(productId);
        verify(productImageRepository, times(1)).save(productImage);
        verify(productImageDtoConverter, times(1)).convertToProductImageDto(productImage);
    }
}