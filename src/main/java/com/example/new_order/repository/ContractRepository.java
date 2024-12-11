package com.example.new_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.new_order.entity.Contract;



@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

}
