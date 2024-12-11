package com.example.new_order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {

   
    private Long menuId;
    private int quantity;
    private String request;
    private String tableNumber;

    
}
