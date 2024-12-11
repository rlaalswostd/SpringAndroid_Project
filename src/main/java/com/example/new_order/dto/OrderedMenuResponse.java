package com.example.new_order.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedMenuResponse {
    private String menuName;      // 메뉴 이름
    private BigDecimal menuPrice; // 메뉴 가격
    private int orderQuantity;    // 주문 수량
    private String tableNumber;   // 테이블 번호
    private String storeId;       // 매장 ID
}