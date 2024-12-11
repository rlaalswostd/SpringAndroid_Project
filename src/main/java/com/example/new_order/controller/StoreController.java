package com.example.new_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.new_order.entity.Store;
import com.example.new_order.service.StoreService;
@RestController
@RequestMapping("/store")
public class StoreController {
    
  @Autowired
    private StoreService storeService;  // StoreService를 의존성 주입


     @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreInfo(@PathVariable String storeId) {
       
        Store store = storeService.getStoreById(storeId); 
        
        if (store != null) {
            return ResponseEntity.ok(store);  // 성공적으로 데이터를 반환
        } else {
            return ResponseEntity.notFound().build();  // Store를 찾을 수 없는 경우
        }
    }
}
