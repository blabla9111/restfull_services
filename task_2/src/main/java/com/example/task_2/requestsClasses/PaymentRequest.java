package com.example.task_2.requestsClasses;

import com.example.task_2.checking.CheckingRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PaymentRequest implements CheckingRequest {
    private String name;

    @JsonProperty("supply_date")
    private LocalDate supplyDate;

    private char part;

    private Long value;

    @Override
    public void checkRequest() {
        if(this.getName()==null || this.getName()==""){
            throw new IllegalArgumentException("Name should be not NULL!");
        }
        if(this.getSupplyDate()==null || this.getSupplyDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Incorrect SupplyDate (=null or future Date)!");
        }
        if(this.getPart()!='k' && this.getPart()!='p'){// what if null
            throw new IllegalArgumentException("Incorrect type of Payment part!");
        }
        if(this.getValue()==null || this.getValue()<=0){
            throw new IllegalArgumentException("Incorrect value field (=null or <=0)");
        }
    }
}
