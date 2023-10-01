package com.kivanc.ecommerce.productservice.dto

import jakarta.validation.constraints.NotBlank

data class CreateCategoryRequest(
    @field:NotBlank
    val categoryName: String
)
