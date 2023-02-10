package com.example.task_2.controllers;

import com.example.task_2.requestsClasses.PaymentRequest;
import com.example.task_2.requestsClasses.SupplyDateRequest;
import com.example.task_2.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/addpayment",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody PaymentRequest paymentRequest) throws JSONException {
        System.out.println(paymentRequest);
        return new ResponseEntity<>(paymentService.addPayment(paymentRequest),
                HttpStatus.valueOf(200));
    }

    @PostMapping(value = "/balancebydate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postDate(@RequestBody SupplyDateRequest supplyDateRequest) throws JSONException {
        return new ResponseEntity<>(paymentService.getBalanceByDate(supplyDateRequest),
                HttpStatus.valueOf(200));
    }


}
