package com.example.new_order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.new_order.entity.Store;
import com.example.new_order.repository.StoreRepository;
@Service  
public class StoreService {
      @Autowired
    private StoreRepository storeRepository;  // StoreRepository는 DB 접근용

    public Store getStoreById(String storeId) {
        return storeRepository.findById(storeId).orElse(null);  // storeId로 Store 조회
    }
}
