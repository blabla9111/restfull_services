package com.example.task_2.services.ipml;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.domain.requestsClasses.PaymentRequest;
import com.example.task_2.domain.requestsClasses.SupplyDateRequest;
import com.example.task_2.repos.PaymentRepo;
import com.example.task_2.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;

    @Override
    public Payment addPayment(PaymentRequest paymentRequest) {
        paymentRequest.checkRequest();
        Payment payment = new Payment(paymentRequest);
        paymentRepo.save(payment);
        return payment;
    }

    @Override
    public Result getBalanceByDate(SupplyDateRequest supplyDateRequest) {
        supplyDateRequest.checkRequest();
        LocalDate date = supplyDateRequest.getSupplyDate();
        LinkedList<Payment> payments = paymentRepo.findAllBySupplyDateIsLessThanEqual(date);
        Long balance = 0L;
        for (Payment payment : payments) {
            if (payment.getPart() == 'k') {
                balance += payment.getValue();
            } else {
                balance -= payment.getValue();
            }
        }
        return new Result(balance);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
