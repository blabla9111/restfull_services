package com.example.task_2.controllers;

import com.example.task_2.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("task_2")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(paymentService.getAllPayments(),
                HttpStatus.valueOf(200));
    }

    @PostMapping(value = "/addpayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(HttpEntity<String> httpEntity) throws JSONException {
        return new ResponseEntity<>(paymentService.addPayment(new JSONObject(httpEntity.getBody())),
                HttpStatus.valueOf(200));
    }

    @PostMapping(value = "/balancebydate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postDate(HttpEntity<String> httpEntity) throws JSONException {
        return new ResponseEntity<>(paymentService.getBalanceByDate(new JSONObject(httpEntity.getBody())),
                HttpStatus.valueOf(200));
    }


}
