package com.kivanc.ecommerce.orderservice.repository;

import com.kivanc.ecommerce.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
