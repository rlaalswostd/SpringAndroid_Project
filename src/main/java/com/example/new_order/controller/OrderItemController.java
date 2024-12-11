package com.example.new_order.controller;

import com.example.new_order.dto.OrderRequest;
import com.example.new_order.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("aa");
        System.out.println(orderRequest.toString());
        System.out.println("Received storeId: " + orderRequest.getStoreId());
        System.out.println("Received tableNumber: " + orderRequest.getTableNumber());
        orderItemService.createOrder(orderRequest);
       
    }
}
