package com.kivanc.ecommerce.productservice.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateProductRequest(
    @field:NotBlank
    val productName: String,
    val details: String,

    @field:Min(1)
    val price: BigDecimal,
    val unitsInStock: Int,

    @field:NotBlank
    val categoryId: String
)
