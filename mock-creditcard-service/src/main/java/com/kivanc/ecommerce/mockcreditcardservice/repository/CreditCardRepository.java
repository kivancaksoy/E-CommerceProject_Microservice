package com.kivanc.ecommerce.mockcreditcardservice.repository;

import com.kivanc.ecommerce.mockcreditcardservice.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    Optional<CreditCard> findByCardNumber(String cardNumber);
}
