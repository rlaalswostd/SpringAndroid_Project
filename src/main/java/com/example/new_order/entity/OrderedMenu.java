package com.example.new_order.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "view_ordered_menu")  // 뷰 이름에 맞게 설정
public class OrderedMenu {

    @Id  // 식별자 필드에 @Id를 추가
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 값 자동 생성 설정
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price", precision = 10, scale = 2)
    private BigDecimal menuPrice;

    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "table_number")
    private String tableNumber;

    @Column(name = "store_id")
    private String storeId;
}
