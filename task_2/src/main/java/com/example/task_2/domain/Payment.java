package com.example.task_2.domain;

import com.example.task_2.requestsClasses.PaymentRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
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

    // А вот так можно?
    public Payment(PaymentRequest paymentRequest){
        this.name= paymentRequest.getName();
        this.supplyDate = paymentRequest.getSupplyDate();
        this.part=paymentRequest.getPart(); // как лучше? Так?
        this.setValue(paymentRequest.getValue()); // Или так?
    }


    // это сравнение только для тестирования, не опасно ли так переопределять?
    @Override
    public boolean equals(Object obj){
        Payment payment = (Payment) obj;
        if (this.name.equals(payment.getName()) && this.part==payment.getPart()
                && this.supplyDate.equals(payment.getSupplyDate()) && this.getValue() == payment.getValue()){
            return true;
        }
        return false;
    }

}
