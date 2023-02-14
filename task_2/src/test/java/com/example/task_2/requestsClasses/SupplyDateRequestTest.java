package com.example.task_2.requestsClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SupplyDateRequestTest {

    LocalDate DEFAULT_DATE = LocalDate.now();

    SupplyDateRequest makeSupplyDateRequest(LocalDate date){
        return SupplyDateRequest
                .builder()
                .supplyDate(date)
                .build();
    }

    @Test
    void checkRequestSuccessful() {
        // Как проверить, что не выброшено ни одно исключение?
        Assertions.assertAll(()->makeSupplyDateRequest(DEFAULT_DATE));
    }

    @Test
    void checkRequestNullDate() {
        SupplyDateRequest sdr = makeSupplyDateRequest(null);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> sdr.checkRequest());
        String expected = "Supply's Date can't be null!";
        Assertions.assertEquals(expected, exception.getMessage());
    }
}