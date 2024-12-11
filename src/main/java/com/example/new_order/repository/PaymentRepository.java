package com.example.new_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.new_order.entity.Payment;



@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
