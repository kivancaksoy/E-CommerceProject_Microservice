package com.kivanc.ecommerce.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "credit_card_payments")
@Data
public class CreditCardPayment extends Payment {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardVerificationValue;
}
