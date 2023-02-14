package com.example.task_2.services.ipml;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.repos.PaymentRepo;
import com.example.task_2.requestsClasses.PaymentRequest;
import com.example.task_2.requestsClasses.SupplyDateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Payment payment = Payment
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(LocalDate.now())
                .value(5000l)
                .build();
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(LocalDate.now())
                .value(5000l)
                .build();
        Payment payment1 = paymentService.addPayment(paymentRequest);
        verify(paymentRepo).save(Mockito.any(Payment.class));
        Assertions.assertEquals(payment,payment1);

    }

    @Test
    void getBalanceByDate() {
        SupplyDateRequest sdr = SupplyDateRequest
                .builder()
                .supplyDate(LocalDate.now())
                .build();
        LinkedList<Payment> payments = new LinkedList<>();
//        LinkedList<Payment> payments = Mockito.mock(LinkedList.class);
        for(int i = 0; i<3;i++){// а кто проверит, что я здесь верно строю payment?
            payments.add(Payment.builder()
                    .value(i+1)
                    .part(i%2==0?'k':'p')
                    .supplyDate(LocalDate.now())
                    .name("111").build());
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