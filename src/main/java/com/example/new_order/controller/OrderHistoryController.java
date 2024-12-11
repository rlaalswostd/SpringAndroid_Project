package com.example.new_order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.new_order.dto.OrderHistoryResponse;
import com.example.new_order.service.OrderHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orderHistory")
@RequiredArgsConstructor
public class OrderHistoryController {
    //해당테이블의 주문내역 가져오기
    private final OrderHistoryService orderHistoryService;

    @GetMapping("/{storeId}/{tableNumber}")
    public List<OrderHistoryResponse> getOrderHistory(@PathVariable String storeId, @PathVariable String tableNumber) {
        return orderHistoryService.getOrderHistory(storeId, tableNumber);
    }
}
