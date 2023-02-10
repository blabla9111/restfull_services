package com.example.task_2.requestsClasses;

import com.example.task_2.checking.CheckingRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SupplyDateRequest implements CheckingRequest {
    @JsonProperty("supply_date")
    private LocalDate supplyDate;

    @Override
    public void checkRequest() {
        if (this.getSupplyDate()==null){
            throw new IllegalArgumentException("Supply's Date can't be null!");
        }
    }
}
