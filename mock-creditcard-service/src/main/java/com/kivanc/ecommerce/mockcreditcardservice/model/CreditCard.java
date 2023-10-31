package com.kivanc.ecommerce.mockcreditcardservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_cards")
@Data
public class CreditCard {
    @Id
    @UuidGenerator
    private String id;

    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardVerificationValue;
    private BigDecimal balance;
}
