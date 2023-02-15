package com.example.task_2.domain.requestsClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SupplyDateRequestTest {

    LocalDate DEFAULT_DATE = LocalDate.now();

    SupplyDateRequest makeSupplyDateRequest(LocalDate date) {
        return new SupplyDateRequest(date);
    }

    @Test
    void checkRequestSuccessful() {
        SupplyDateRequest sdr = makeSupplyDateRequest(DEFAULT_DATE);
        Assertions.assertAll(() -> sdr.checkRequest());
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