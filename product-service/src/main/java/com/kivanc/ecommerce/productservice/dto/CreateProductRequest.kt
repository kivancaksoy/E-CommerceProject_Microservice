package com.kivanc.ecommerce.productservice.dto

import java.math.BigDecimal

data class CreateProductRequest(
    val productName: String,
    val details: String,
    val price: BigDecimal,
    val unitsInStock: Int,
    val categoryId: String,
)
