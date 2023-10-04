package com.kivanc.ecommerce.productservice.resource;

import com.kivanc.ecommerce.productservice.dto.ProductImageDto;
import com.kivanc.ecommerce.productservice.service.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/productimage")
public class ProductImageController {
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductImageDto> uploadProductImage(@RequestPart("imageFile") MultipartFile imageFile,
                                                              @RequestParam("productId") String productId) throws IOException {
        return ResponseEntity.ok(productImageService.uploadProductImage(imageFile, productId));
    }
}
