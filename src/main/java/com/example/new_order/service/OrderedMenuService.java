package com.example.new_order.service;

import com.example.new_order.dto.OrderedMenuResponse;
import com.example.new_order.repository.OrderedMenuRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderedMenuService {

    private final OrderedMenuRepository orderedMenuRepository;

    public OrderedMenuService(OrderedMenuRepository orderedMenuRepository) {
        this.orderedMenuRepository = orderedMenuRepository;
    }

    public List<OrderedMenuResponse> getOrderedMenus(String tableNumber, String storeId) {
        return orderedMenuRepository.findOrderedMenusByTableAndStore(tableNumber, storeId);
    }
}