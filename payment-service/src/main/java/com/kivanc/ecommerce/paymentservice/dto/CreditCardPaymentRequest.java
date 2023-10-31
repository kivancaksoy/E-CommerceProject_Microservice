package com.kivanc.ecommerce.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditCardPaymentRequest {
    private BigDecimal amount;
    private String userId;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardVerificationValue;

    public CreditCardPurchaseRequest convertToCreditCardPurchaseRequest() {
        CreditCardPurchaseRequest creditCardPurchaseRequest = new CreditCardPurchaseRequest();
        creditCardPurchaseRequest.setFirstName(firstName);
        creditCardPurchaseRequest.setLastName(lastName);
        creditCardPurchaseRequest.setCardNumber(cardNumber);
        creditCardPurchaseRequest.setExpirationDate(expirationDate);
        creditCardPurchaseRequest.setCardVerificationValue(cardVerificationValue);
        creditCardPurchaseRequest.setPrice(amount);
        return creditCardPurchaseRequest;
    }
}
