package com.example.task_2.services;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface PaymentService {
    Payment addPayment(JSONObject json) throws JSONException;

    Result getBalanceByDate(JSONObject json) throws JSONException;

    List<Payment> getAllPayments();
}
