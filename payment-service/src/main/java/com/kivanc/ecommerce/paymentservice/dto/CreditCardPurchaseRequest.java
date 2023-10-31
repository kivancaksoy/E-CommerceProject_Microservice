package com.kivanc.ecommerce.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditCardPurchaseRequest {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardVerificationValue;
    private BigDecimal price;
}
