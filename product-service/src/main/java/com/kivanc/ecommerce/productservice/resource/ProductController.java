package com.kivanc.ecommerce.productservice.resource;

import com.kivanc.ecommerce.productservice.dto.CreateProductRequest;
import com.kivanc.ecommerce.productservice.dto.ProductDto;
import com.kivanc.ecommerce.productservice.dto.UpdateProductStockRequest;
import com.kivanc.ecommerce.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getById")
    public ResponseEntity<ProductDto> getProductById(@RequestParam(name = "id") @NotEmpty String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/updateStock")
    public ResponseEntity<Void> updateStock(@RequestBody List<UpdateProductStockRequest> updateProductStockRequestList) {
        productService.updateStock(updateProductStockRequestList);
        return ResponseEntity.ok().build();
    }
}
