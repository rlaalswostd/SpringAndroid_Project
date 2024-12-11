package com.example.new_order.controller;

import com.example.new_order.dto.OrderedMenuResponse;
import com.example.new_order.service.OrderedMenuService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderedMenuController {

    private final OrderedMenuService orderedMenuService;

    public OrderedMenuController(OrderedMenuService orderedMenuService) {
        this.orderedMenuService = orderedMenuService;
    }

    // 메뉴 내역 조회 API
    @GetMapping("/ordered-menus/{storeId}/{tableNumber}")
    public List<OrderedMenuResponse> getOrderedMenus(
            @PathVariable String storeId,
            @PathVariable String tableNumber) {
        return orderedMenuService.getOrderedMenus(tableNumber, storeId);
    }
}
