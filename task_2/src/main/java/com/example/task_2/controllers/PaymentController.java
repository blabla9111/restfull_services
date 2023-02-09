package com.example.task_2.controllers;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.repos.PaymentRepo;
import com.example.task_2.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


import java.time.LocalDate;
import java.util.LinkedList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("task_2")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepo paymentRepo;

    @GetMapping()
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(paymentRepo.findAll(), HttpStatus.valueOf(200));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(HttpEntity<String> httpEntity) throws JSONException {
        String body = httpEntity.getBody();
        JSONObject json = new JSONObject(body);
        Payment payment = new Payment();
        payment.setName(json.get("name").toString());
        payment.setSupplyDate(LocalDate.parse(json.getString("supplyDate")));
        payment.setPart(json.getString("part").charAt(0));
        payment.setValue(Long.parseLong(json.getString("value")));
        paymentRepo.save(payment);
        return new ResponseEntity<>(paymentRepo.findAll(), HttpStatus.valueOf(200));
    }

    @PostMapping(value = "/balancebydate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postDate(HttpEntity<String> httpEntity) throws JSONException {
        String body = httpEntity.getBody();
        JSONObject json = new JSONObject(body);
        LocalDate date = LocalDate.parse(json.getString("supplyDate"));
        LinkedList<Payment> payments = paymentRepo.findAllBySupplyDateIsLessThanEqual(date);
        Long balance = 0l;
        for (Payment payment : payments) {
            if (payment.getPart() == 'k') {
                balance += payment.getValue();
            } else if (payment.getPart() == 'p') {
                balance -= payment.getValue();
            } else {
                System.out.println("AAAAlert!!!");
            }
        }
        Result result = new Result(balance);
        return new ResponseEntity<>(result, HttpStatus.valueOf(200));
    }


}
