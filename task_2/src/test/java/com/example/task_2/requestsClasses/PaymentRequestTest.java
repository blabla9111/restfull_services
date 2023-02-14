package com.example.task_2.requestsClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

class PaymentRequestTest {

    String DEFAULT_NAME = "Blabla";
    char DEFAULT_PART = 'k';
    LocalDate DEFAULT_DATE = LocalDate.now();
    Long DEFAULT_VALUE = 5000L;

    PaymentRequest makePaymentRequest(String name, char part, LocalDate date, Long value) {
        return PaymentRequest
                .builder()
                .name(name)
                .part(part)
                .supplyDate(date)
                .value(value)
                .build();
    }

    @Test
    void checkRequestSuccessful() {
        // Как проверить, что не выброшено ни одно исключение?
        Assertions.assertAll(()->makePaymentRequest(DEFAULT_NAME, DEFAULT_PART, DEFAULT_DATE, DEFAULT_VALUE));
    }

    @Test
    void checkRequestNameException() {
        PaymentRequest pr = makePaymentRequest("", DEFAULT_PART, DEFAULT_DATE, DEFAULT_VALUE);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Name should be not NULL!";
        Assertions.assertTrue(expected.contains(exception.getMessage()));

    }

    @Test
    void checkRequestSupplyDateExceptionFutureDate() {
        PaymentRequest pr = makePaymentRequest(DEFAULT_NAME, DEFAULT_PART, LocalDate.now().plusDays(1), DEFAULT_VALUE);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Incorrect SupplyDate (=null or future Date)!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void checkRequestSupplyDateExceptionNullDate() {
        PaymentRequest pr = makePaymentRequest(DEFAULT_NAME, DEFAULT_PART, null, DEFAULT_VALUE);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Incorrect SupplyDate (=null or future Date)!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(chars = {'y', 'w', ' '})
    void checkRequestPartException(char part) {
        PaymentRequest pr = makePaymentRequest(DEFAULT_NAME, part, DEFAULT_DATE, DEFAULT_VALUE);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Incorrect type of Payment part!";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1000, 0})
    void checkRequestValueException(long num) {
        PaymentRequest pr = makePaymentRequest(DEFAULT_NAME, DEFAULT_PART, DEFAULT_DATE, num);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Incorrect value field (=null or <=0)";
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void checkRequestValueNullException() {
        PaymentRequest pr = makePaymentRequest(DEFAULT_NAME, DEFAULT_PART, DEFAULT_DATE, null);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> pr.checkRequest());
        String expected = "Incorrect value field (=null or <=0)";
        Assertions.assertEquals(expected, exception.getMessage());
    }

}