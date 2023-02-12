package com.example.task_2.services.ipml;

import com.example.task_2.domain.Payment;
import com.example.task_2.domain.Result;
import com.example.task_2.repos.PaymentRepo;
import com.example.task_2.requestsClasses.PaymentRequest;
import com.example.task_2.requestsClasses.SupplyDateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Answers;
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

//    PaymentServiceImplTest(PaymentRepo paymentRepo) {
//        this.paymentRepo = paymentRepo;
//    }

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
    void addPaymentNameException() {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("")
                .part('k')
                .supplyDate(LocalDate.now())
                .value(5000l)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Name should be not NULL!";
        Assertions.assertTrue(expected.contains(exception.getMessage()));

    }

    @Test
    void addPaymentSupplyDateExceptionFutureDate() {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(LocalDate.now().plusDays(1))
                .value(5000l)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Incorrect SupplyDate (=null or future Date)!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void addPaymentSupplyDateExceptionNullDate() {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(null)
                .value(5000l)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Incorrect SupplyDate (=null or future Date)!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(chars = {'y','w',' '})
    void addPaymentPartException(char part) {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part(part)
                .supplyDate(LocalDate.now())
                .value(5000l)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Incorrect type of Payment part!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1000, 0})
    void addPaymentValueException(long num) {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(LocalDate.now())
                .value(num)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Incorrect value field (=null or <=0)";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void addPaymentValueNullException() {
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .name("Dinara")
                .part('k')
                .supplyDate(LocalDate.now())
                .value(null)
                .build();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                ()->paymentService.addPayment(paymentRequest));
        String expected = "Incorrect value field (=null or <=0)";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void getBalanceByDate() {
        SupplyDateRequest sdr = SupplyDateRequest
                .builder()
                .supplyDate(LocalDate.now())
                .build();
        LinkedList<Payment> payments = new LinkedList<>();
        for(int i = 0; i<3;i++){
            payments.add(Payment.builder()
                    .value(i+1)
                    .part('k')
                    .supplyDate(LocalDate.now())
                    .name("111").build());
        }

    //        Mockito.when(paymentRepo.findAllBySupplyDateIsLessThanEqual(Mockito.any())).thenReturn(payments);
//        Assertions.assertEquals(payments, paymentRepo.findAllBySupplyDateIsLessThanEqual(LocalDate.now()));
        Mockito.when(paymentRepo.findAllBySupplyDateIsLessThanEqual(any())).thenReturn(payments);
        Result result = paymentService.getBalanceByDate(sdr);
        verify(paymentRepo).findAllBySupplyDateIsLessThanEqual(any());
        Assertions.assertEquals(6l, result.getBalance());
    }

    @Test
    void getAllPayments() {
////        List list = new List();
//        Mockito.when(paymentRepo.findAll()).thenReturn(null);
//        Assertions.assertEquals(null, paymentService.getAllPayments());
    }
}