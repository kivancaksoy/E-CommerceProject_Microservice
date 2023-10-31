package com.kivanc.ecommerce.paymentservice.service;

import com.kivanc.ecommerce.paymentservice.dto.CreditCardPaymentRequest;
import com.kivanc.ecommerce.paymentservice.dto.converter.CreditCardPaymentConverter;
import com.kivanc.ecommerce.paymentservice.model.CreditCardPayment;
import com.kivanc.ecommerce.paymentservice.repository.CreditCardPaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditCardPaymentService {
    private final CreditCardPaymentRepository creditCardPaymentRepository;
    private final CreditCardPaymentConverter creditCardPaymentConverter;
    private final RestTemplate restTemplate;

    //TODO write to constans below.
    private String creditCardUrl = "http://localhost:8001/v1/creditCard/pay";

    public CreditCardPaymentService(CreditCardPaymentRepository creditCardPaymentRepository, CreditCardPaymentConverter creditCardPaymentConverter, RestTemplate restTemplate) {
        this.creditCardPaymentRepository = creditCardPaymentRepository;
        this.creditCardPaymentConverter = creditCardPaymentConverter;
        this.restTemplate = restTemplate;
    }

    public void pay(CreditCardPaymentRequest creditCardPaymentRequest) {
        CreditCardPayment creditCardPayment = creditCardPaymentConverter.convertToCreditCardPayment(creditCardPaymentRequest);
        // send POST request with RestTemplate
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(creditCardUrl, creditCardPaymentRequest.convertToCreditCardPurchaseRequest(), String.class);

        // status code of the response
        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (statusCode == HttpStatus.OK)
            creditCardPaymentRepository.save(creditCardPayment);
    }
}
