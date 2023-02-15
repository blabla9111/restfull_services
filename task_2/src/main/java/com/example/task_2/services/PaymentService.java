package com.example.task_2.services;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.domain.requestsClasses.PaymentRequest;
import com.example.task_2.domain.requestsClasses.SupplyDateRequest;

import java.util.List;

public interface PaymentService {
    Payment addPayment(PaymentRequest paymentRequest);

    Result getBalanceByDate(SupplyDateRequest supplyDateRequest);

    List<Payment> getAllPayments();
}
