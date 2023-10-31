package com.kivanc.ecommerce.mockcreditcardservice.dto;

import com.kivanc.ecommerce.mockcreditcardservice.model.CreditCard;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class CreditCardPurchaseRequest {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardVerificationValue;
    private BigDecimal price;

    public boolean isEquivalentTo(CreditCard creditCard) {
        return Objects.equals(creditCard.getFirstName(), firstName) &&
                Objects.equals(creditCard.getLastName(), lastName) &&
                Objects.equals(creditCard.getCardNumber(), cardNumber) &&
                Objects.equals(creditCard.getExpirationDate(), expirationDate) &&
                Objects.equals(creditCard.getCardVerificationValue(), cardVerificationValue);
    }

    /*public CreditCard convertToCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setFirstName(firstName);
        return creditCard;
    }*/
}
