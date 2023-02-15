package com.example.task_2.services.ipml;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.domain.requestsClasses.PaymentRequest;
import com.example.task_2.domain.requestsClasses.SupplyDateRequest;
import com.example.task_2.repos.PaymentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepo paymentRepo;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void addPaymentSuccessful() {
        PaymentRequest paymentRequest = mock(PaymentRequest.class);
        when(paymentRequest.getName()).thenReturn("nhhh");
        when(paymentRequest.getPart()).thenReturn('k');
        when(paymentRequest.getSupplyDate()).thenReturn(LocalDate.now());
        when(paymentRequest.getValue()).thenReturn(4000L);
        Payment payment = paymentService.addPayment(paymentRequest);
        verify(paymentRepo).save(any(Payment.class));
        Assertions.assertEquals(payment.getName(), paymentRequest.getName());
        Assertions.assertEquals(payment.getPart(), paymentRequest.getPart());
        Assertions.assertEquals(payment.getSupplyDate(), paymentRequest.getSupplyDate());
        Assertions.assertEquals(payment.getValue(), paymentRequest.getValue());

    }

    @Test
    void getBalanceByDate() {
        SupplyDateRequest sdr = mock(SupplyDateRequest.class);
        when(sdr.getSupplyDate()).thenReturn(LocalDate.now());
        LinkedList<Payment> payments = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Payment payment = new Payment();
            payment.setName("FFFF");
            payment.setSupplyDate(LocalDate.now());
            payment.setPart(i % 2 == 0 ? 'k' : 'p');
            payment.setValue(i + 1);
            payments.add(payment);
        }
        when(paymentRepo.findAllBySupplyDateIsLessThanEqual(any())).thenReturn(payments);
        Result result = paymentService.getBalanceByDate(sdr);
        verify(paymentRepo).findAllBySupplyDateIsLessThanEqual(any());
        Assertions.assertEquals(2l, result.getBalance());
    }

    @Test
    void getAllPayments() {
    }
}