package com.example.new_order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.new_order.entity.Tables;


@Repository 
public interface TablesRepository extends JpaRepository<Tables, Integer> {

    Optional<Tables> findByStoreStoreIdAndTableNumber(String storeId, String tableNumber);  

    //테이블이 존재하는지 -> 테이블체인저 검증
    boolean existsByStoreStoreIdAndTableNumber(String storeId, String tableNumber);  


}