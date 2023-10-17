package com.kivanc.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @UuidGenerator
    private String id;

    private String productId;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
    private Order order;
}
