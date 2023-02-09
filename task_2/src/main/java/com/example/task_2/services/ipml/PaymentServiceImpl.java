package com.example.task_2.services.ipml;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.repos.PaymentRepo;
import com.example.task_2.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;

    @Override
    public Payment addPayment(JSONObject json) throws JSONException {
        Payment payment = new Payment();
        payment.setName(json.get("name").toString());
        payment.setSupplyDate(LocalDate.parse(json.getString("supplyDate")));
        payment.setPart(json.getString("part").charAt(0));
        payment.setValue(Long.parseLong(json.getString("value")));
        paymentRepo.save(payment);
        return payment;
    }

    @Override
    public Result getBalanceByDate(JSONObject json) throws JSONException {
        LocalDate date = LocalDate.parse(json.getString("supplyDate"));
        LinkedList<Payment> payments = paymentRepo.findAllBySupplyDateIsLessThanEqual(date);
        Long balance = 0l;
        for (Payment payment : payments) {
            if (payment.getPart() == 'k') {
                balance += payment.getValue();
            } else if (payment.getPart() == 'p') {
                balance -= payment.getValue();
            } else {
//                System.out.println("Another type of part");
            }
        }
        return new Result(balance);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
