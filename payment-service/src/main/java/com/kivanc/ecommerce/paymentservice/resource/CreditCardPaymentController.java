package com.kivanc.ecommerce.paymentservice.resource;

import com.kivanc.ecommerce.paymentservice.dto.CreditCardPaymentRequest;
import com.kivanc.ecommerce.paymentservice.service.CreditCardPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/creditCardPayment")
public class CreditCardPaymentController {
    private final CreditCardPaymentService creditCardPaymentService;

    public CreditCardPaymentController(CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody CreditCardPaymentRequest creditCardPaymentRequest) {
        creditCardPaymentService.pay(creditCardPaymentRequest);
        return ResponseEntity.ok().build();
    }
}
