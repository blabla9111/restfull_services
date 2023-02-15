package com.example.task_2.repos;

import com.example.task_2.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.LinkedList;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    LinkedList<Payment> findAllBySupplyDateIsLessThanEqual(LocalDate date);
}
