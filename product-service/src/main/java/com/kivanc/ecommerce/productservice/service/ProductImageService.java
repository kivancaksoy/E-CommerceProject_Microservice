package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductImageDtoConverter;
import com.kivanc.ecommerce.productservice.helper.LocalStorageService;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import com.kivanc.ecommerce.productservice.repository.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageDtoConverter productImageDtoConverter;
    private final ProductService productService;
    private final LocalStorageService localStorageService;

    @Value("${imageFolderPath}") //TODO get from vault
    String folderPath;

    public ProductImageService(ProductImageRepository productImageRepository, ProductImageDtoConverter productImageDtoConverter, ProductService productService, LocalStorageService localStorageService) {
        this.productImageRepository = productImageRepository;
        this.productImageDtoConverter = productImageDtoConverter;
        this.productService = productService;
        this.localStorageService = localStorageService;
    }

    @Transactional
    public ProductImageDto uploadProductImage(MultipartFile imageFile, String productId) throws IOException {

        localStorageService.uploadImage(imageFile, folderPath);

        ProductImage productImage = new ProductImage(
                Objects.requireNonNull(imageFile.getOriginalFilename()),
                folderPath,
                productService.findProductById(productId)
        );

        return productImageDtoConverter.convertToProductImageDto(productImageRepository.save(productImage));
    }
}
