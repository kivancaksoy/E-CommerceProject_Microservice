package com.kivanc.ecommerce.productservice.resource;

import com.kivanc.ecommerce.productservice.dto.CreateProductImageRequest;
import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.service.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/productimage")
public class ProductImageController {
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductImageDto> createProductImage(@RequestBody CreateProductImageRequest createProductImageRequest) {
        return ResponseEntity.ok(productImageService.createProductImage(createProductImageRequest));
    }
}
