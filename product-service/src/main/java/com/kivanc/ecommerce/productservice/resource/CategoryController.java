package com.kivanc.ecommerce.productservice.resource;

import com.kivanc.ecommerce.productservice.dto.CategoryDto;
import com.kivanc.ecommerce.productservice.dto.CreateCategoryRequest;
import com.kivanc.ecommerce.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam(name = "id") @NotBlank String id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
