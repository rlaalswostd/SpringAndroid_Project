package com.example.new_order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequest {

    private String tableNumber; // 테이블 ID
    private List<OrderItemRequest> items; // 주문 항목 리스트
    private String storeId; 
    
    @Getter
    @Setter
    @ToString
    public static class OrderItemRequest {
        private Long menuId; // 메뉴 ID
        private Integer quantity; // 수량
        private String request; // 요청 사항
    }
}
