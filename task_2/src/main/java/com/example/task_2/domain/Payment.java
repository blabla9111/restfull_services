package com.example.task_2.domain;

import com.example.task_2.domain.requestsClasses.PaymentRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private LocalDate supplyDate;
    private char part;
    private long value;

    public Payment(PaymentRequest paymentRequest){
        this.name= paymentRequest.getName();
        this.supplyDate = paymentRequest.getSupplyDate();
        this.part=paymentRequest.getPart();
        this.value=paymentRequest.getValue();
    }

}
