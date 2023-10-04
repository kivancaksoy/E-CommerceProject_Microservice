package com.kivanc.ecommerce.productservice.dto

data class CreateProductImageRequest(
    val imageName: String,
    val imagePath: String,
    val productId: String
)
