package com.example.task_2.domain;

import com.example.task_2.requestsClasses.PaymentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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

    // А вот так можно?
    public Payment(PaymentRequest paymentRequest){
        this.name= paymentRequest.getName();
        this.supplyDate = paymentRequest.getSupplyDate();
        this.part=paymentRequest.getPart(); // как лучше? Так?
        this.setValue(paymentRequest.getValue()); // Или так?
    }


}
