package com.kivanc.ecommerce.productservice.dto

import java.math.BigDecimal

data class ProductDto(
    val id: String,
    val productName: String,
    val details: String,
    val price: BigDecimal,
    val category: CategoryDto,
)
