package com.kivanc.ecommerce.paymentservice.repository;

import com.kivanc.ecommerce.paymentservice.model.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, String> {
}
