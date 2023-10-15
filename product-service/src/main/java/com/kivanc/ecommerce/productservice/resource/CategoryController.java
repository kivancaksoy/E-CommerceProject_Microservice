package com.kivanc.ecommerce.productservice.resource;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateCategoryRequest;
import com.kivanc.ecommerce.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@Validated
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    private final Environment environment;

    public CategoryController(CategoryService categoryService, Environment environment) {
        this.categoryService = categoryService;
        this.environment = environment;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        logger.info("Category created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        logger.info("Get all categories on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam(name = "id") @NotBlank String id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
