package com.example.task_2.domain.requestsClasses;

import com.example.task_2.domain.requestsClasses.interfaces.CheckingRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SupplyDateRequest implements CheckingRequest {
    @JsonProperty("supply_date")
    private LocalDate supplyDate;

    SupplyDateRequest() {
    }

    SupplyDateRequest(LocalDate date) {
        this.supplyDate = date;
    }

    @Override
    public void checkRequest() {
        if (this.getSupplyDate() == null) {
            throw new IllegalArgumentException("Supply's Date can't be null!");
        }
    }
}
