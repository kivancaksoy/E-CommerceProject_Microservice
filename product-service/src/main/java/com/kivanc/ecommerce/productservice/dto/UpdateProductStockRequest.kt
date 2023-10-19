package com.kivanc.ecommerce.productservice.dto

data class UpdateProductStockRequest(
    val id:String,
    val quantity: Int
) {
}