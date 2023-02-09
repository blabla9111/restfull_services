package com.example.task_2.domain;

import lombok.Data;

@Data
public class Result {
    Long balance;

    public Result(Long balance) {
        this.balance = balance;
    }
}
