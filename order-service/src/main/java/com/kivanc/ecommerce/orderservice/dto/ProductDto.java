package com.kivanc.ecommerce.orderservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        BigDecimal price,
        Integer unitsInStock
) {
}
