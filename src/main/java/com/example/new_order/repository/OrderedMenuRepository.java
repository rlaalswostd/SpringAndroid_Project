package com.example.new_order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.new_order.dto.OrderedMenuResponse;
import com.example.new_order.entity.OrderedMenu;

public interface OrderedMenuRepository extends JpaRepository<OrderedMenu, Long> {
    @Query(value = """
        SELECT new com.example.new_order.dto.OrderedMenuResponse(
            m.menuName,
            m.menuPrice,
            m.orderQuantity,
            m.tableNumber,
            m.storeId
        )
        FROM OrderedMenu m
        WHERE m.tableNumber = :tableNumber
        AND m.storeId = :storeId
        ORDER BY m.id DESC
    """)
    List<OrderedMenuResponse> findOrderedMenusByTableAndStore(String tableNumber, String storeId);

}
