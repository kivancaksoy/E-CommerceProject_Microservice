package com.kivanc.ecommerce.mockcreditcardservice.service;

import com.kivanc.ecommerce.mockcreditcardservice.dto.CreditCardPurchaseRequest;
import com.kivanc.ecommerce.mockcreditcardservice.model.CreditCard;
import com.kivanc.ecommerce.mockcreditcardservice.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public void processPurchase(CreditCardPurchaseRequest creditCardPurchaseRequest) {
        if (creditCardPurchaseRequest.getExpirationDate().isBefore(LocalDate.now()))
            throw new RuntimeException("The credit card has expired.");
        CreditCard creditCard = creditCardRepository.findByCardNumber(creditCardPurchaseRequest.getCardNumber())
                .orElseThrow(() -> new RuntimeException("Credit card does not exist."));

        if (!creditCardPurchaseRequest.isEquivalentTo(creditCard)) {
            throw new RuntimeException("Credit card information does not match.");
        }
        if (creditCardPurchaseRequest.getPrice().compareTo(creditCard.getBalance()) > 0)
            throw new RuntimeException("The credit card balance is insufficient.");
        creditCard.setBalance(creditCard.getBalance().subtract(creditCardPurchaseRequest.getPrice()));
        creditCardRepository.save(creditCard);
    }
}
