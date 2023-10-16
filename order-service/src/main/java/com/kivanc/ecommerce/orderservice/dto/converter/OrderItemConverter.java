package com.kivanc.ecommerce.orderservice.dto.converter;

import com.kivanc.ecommerce.orderservice.dto.OrderItemDto;
import com.kivanc.ecommerce.orderservice.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getProductId(),
                orderItem.getQuantity()
        );
    }

    public OrderItem convertToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.productId());
        orderItem.setQuantity(orderItemDto.quantity());

        return orderItem;
    }
}
