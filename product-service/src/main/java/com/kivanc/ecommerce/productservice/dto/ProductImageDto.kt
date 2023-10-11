package com.kivanc.ecommerce.productservice.dto

data class ProductImageDto(
    val id: String,
    val imageName: String,
    val folderPath: String,
    val productId: String
)