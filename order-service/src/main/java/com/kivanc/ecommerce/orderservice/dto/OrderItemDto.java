package com.kivanc.ecommerce.orderservice.dto;

public record OrderItemDto(
        String productId,
        Integer quantity
) {
}
