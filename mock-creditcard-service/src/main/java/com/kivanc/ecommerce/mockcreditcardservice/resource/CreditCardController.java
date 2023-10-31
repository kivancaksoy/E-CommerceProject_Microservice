package com.kivanc.ecommerce.mockcreditcardservice.resource;

import com.kivanc.ecommerce.mockcreditcardservice.dto.CreditCardPurchaseRequest;
import com.kivanc.ecommerce.mockcreditcardservice.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/creditCard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("pay")
    public ResponseEntity<Void> pay(@RequestBody CreditCardPurchaseRequest creditCardPurchaseRequest) {
        creditCardService.processPurchase(creditCardPurchaseRequest);
        return ResponseEntity.ok().build();
    }
}
