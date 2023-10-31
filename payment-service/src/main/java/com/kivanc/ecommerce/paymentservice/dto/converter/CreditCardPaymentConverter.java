package com.kivanc.ecommerce.paymentservice.dto.converter;

import com.kivanc.ecommerce.paymentservice.dto.CreditCardPaymentRequest;
import com.kivanc.ecommerce.paymentservice.model.CreditCardPayment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreditCardPaymentConverter {
    public CreditCardPayment convertToCreditCardPayment(CreditCardPaymentRequest creditCardPaymentRequest) {
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setAmount(creditCardPaymentRequest.getAmount());
        creditCardPayment.setUserId(creditCardPaymentRequest.getUserId());
        creditCardPayment.setCreatedDate(LocalDateTime.now());
        creditCardPayment.setFirstName(creditCardPaymentRequest.getFirstName());
        creditCardPayment.setLastName(creditCardPaymentRequest.getLastName());
        creditCardPayment.setCardNumber(creditCardPaymentRequest.getCardNumber());
        creditCardPayment.setExpirationDate(creditCardPaymentRequest.getExpirationDate());
        creditCardPayment.setCardVerificationValue(creditCardPaymentRequest.getCardVerificationValue());
        return creditCardPayment;
    }
}
