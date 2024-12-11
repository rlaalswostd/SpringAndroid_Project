package com.example.new_order.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.new_order.repository.OrdersRepository;

import jakarta.transaction.Transactional;
@Service
public class OrdersStatusService {
 
    

    @Autowired
    private OrdersRepository ordersRepository;

     @Modifying
    @Transactional
    public int updateStatusToCompleted(String storeId, String tableNumber) {
        return ordersRepository.updateStatusToCompleted(storeId, tableNumber,LocalDateTime.now());
    }
}

