package com.example.new_order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.new_order.service.OrdersStatusService;

@RestController
@RequestMapping("/api/orders")
public class OrderStatusController {

 @Autowired
    private OrdersStatusService ordersService;

    // 주문 상태를 'COMPLETED'로 변경하는 엔드포인트
    @PostMapping("/update-status/{storeId}/{tableNumber}")
    public ResponseEntity<Map<String, Object>> updateOrderStatusToCompleted(@PathVariable String storeId, @PathVariable String tableNumber) {
        int updatedRows = ordersService.updateStatusToCompleted(storeId, tableNumber);

        Map<String, Object> response = new HashMap<>();
        if (updatedRows > 0) {
            response.put("message", "Order status updated to COMPLETED.");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "No orders found or update failed.");
            response.put("status", "failure");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
