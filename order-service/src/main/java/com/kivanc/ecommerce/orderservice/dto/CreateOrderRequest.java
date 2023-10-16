package com.kivanc.ecommerce.orderservice.dto;

import java.util.List;

public record CreateOrderRequest(
        List<OrderItemDto> orderItems
) {

}
