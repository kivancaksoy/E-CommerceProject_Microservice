package com.kivanc.ecommerce.paymentservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Payment {
    @Id
    @UuidGenerator
    private String id;

    private BigDecimal amount;
    private String userId;
    private LocalDateTime createdDate;
}
