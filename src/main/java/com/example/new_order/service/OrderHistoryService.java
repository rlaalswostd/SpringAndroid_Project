package com.example.new_order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.new_order.dto.OrderHistoryResponse;
import com.example.new_order.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {
    private final OrdersRepository ordersRepository;

    public List<OrderHistoryResponse> getOrderHistory(String storeId, String tableNumber) {
        // OrdersRepository에서 storeId와 tableId를 사용해 주문 내역 조회
        return ordersRepository.findOrderHistoryByStoreIdAndTableNumber(storeId, tableNumber);
    }
}
