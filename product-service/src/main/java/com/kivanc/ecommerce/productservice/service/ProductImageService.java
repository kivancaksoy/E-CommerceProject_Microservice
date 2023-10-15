package com.kivanc.ecommerce.productservice.service;

import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.dto.converter.ProductImageDtoConverter;
import com.kivanc.ecommerce.productservice.helper.FileNameHelper;
import com.kivanc.ecommerce.productservice.helper.LocalStorageService;
import com.kivanc.ecommerce.productservice.model.ProductImage;
import com.kivanc.ecommerce.productservice.repository.ProductImageRepository;
import com.kivanc.ecommerce.productservice.util.ProductImageBusinessRuleUtil;
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

    @Value("${product-image-service.image.folder.path}")
    String folderPath;

    public ProductImageService(ProductImageRepository productImageRepository, ProductImageDtoConverter productImageDtoConverter, ProductService productService, LocalStorageService localStorageService) {
        this.productImageRepository = productImageRepository;
        this.productImageDtoConverter = productImageDtoConverter;
        this.productService = productService;
        this.localStorageService = localStorageService;
    }

    @Transactional
    public ProductImageDto uploadProductImage(MultipartFile imageFile, String productId) throws IOException {
        ProductImageBusinessRuleUtil.checkProductImageCount(productImageRepository.findByProductId(productId).size());

        String newFileName = FileNameHelper.generateRandomFileName(Objects.requireNonNull(imageFile.getOriginalFilename()));

        ProductImage productImage = new ProductImage(
                newFileName,
                folderPath,
                productService.findProductById(productId)
        );

        localStorageService.uploadImage(imageFile, folderPath, newFileName);

        return productImageDtoConverter.convertToProductImageDto(productImageRepository.save(productImage));
    }
}
