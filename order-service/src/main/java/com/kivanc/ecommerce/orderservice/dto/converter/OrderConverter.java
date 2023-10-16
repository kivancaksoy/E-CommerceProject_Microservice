package com.kivanc.ecommerce.orderservice.dto.converter;

import com.kivanc.ecommerce.orderservice.dto.CreateOrderRequest;
import com.kivanc.ecommerce.orderservice.model.Order;
import com.kivanc.ecommerce.orderservice.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderConverter(OrderItemConverter orderItemConverter) {
        this.orderItemConverter = orderItemConverter;
    }

    public Order convertToOrder(CreateOrderRequest createOrderRequest, BigDecimal totalPrice) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("received");
        order.setTotalPrice(totalPrice);
        List<OrderItem> orderItems = createOrderRequest.orderItems()
                .stream()
                .map(orderItemConverter::convertToOrderItem)
                .collect(Collectors.toList());
        orderItems.forEach(orderItem -> orderItem.setOrder(order));
        order.setOrderItems(orderItems);


        return order;
    }
}
