package com.example.new_order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.new_order.dto.OrderHistoryResponse;
import com.example.new_order.entity.Orders;
import jakarta.transaction.Transactional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    //public int countByOrderIdStartingWith(String date);

    // Long 타입을 String으로 변환하여 LIKE 연산을 사용
    @Query("SELECT COUNT(o) FROM Orders o " +
           "WHERE CAST(o.orderId AS string) LIKE :datePrefix%")
    int countByOrderIdStartingWith(@Param("datePrefix") String datePrefix);

    // 수정된 countByOrderIdStartingWith 메서드 (Long 값 비교로 수정)
    @Query("SELECT COUNT(o) FROM Orders o " +
           "WHERE o.orderId >= :dateStart AND o.orderId < :dateEnd")
    int countByOrderIdStartingWith(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query("SELECT new com.example.new_order.dto.OrderHistoryResponse(" +
            "SUM(o.totalAmount), MAX(o.createdAt), o.tables.tableNumber, o.tables.store.storeId) " +
            "FROM Orders o " +
            "WHERE o.tables.store.storeId = :storeId AND o.tables.tableNumber = :tableNumber AND o.status = 'ORDERED' " +
            "GROUP BY o.tables.tableNumber, o.tables.store.storeId")
    List<OrderHistoryResponse> findOrderHistoryByStoreIdAndTableNumber(@Param("storeId") String storeId,
            @Param("tableNumber") String tableNumber);


            //주문상태를 status로 변경하는 쿼리
   @Modifying
@Transactional
@Query("UPDATE Orders o " +
       "SET o.status = 'COMPLETED', " +
       "o.paymentTime = :paymentTime " +
       "WHERE o.tables.store.storeId = :storeId " +
       "AND o.tables.tableNumber = :tableNumber " +
       "AND o.status = 'ORDERED'")
int updateStatusToCompleted(
        @Param("storeId") String storeId, 
        @Param("tableNumber") String tableNumber,
        @Param("paymentTime") LocalDateTime paymentTime
);


   @Modifying
@Transactional
@Query(value = "INSERT INTO orders_sequence (date, sequence_value) " +
        "VALUES (CURRENT_DATE, 101) " +
        "ON DUPLICATE KEY UPDATE sequence_value = sequence_value + 1", nativeQuery = true)
void incrementOrderSequence();

@Query(value = "SELECT sequence_value FROM orders_sequence WHERE date = CURRENT_DATE", nativeQuery = true)
Long getCurrentOrderSequence();

@Transactional
default Long getNextOrderSequence() {
    incrementOrderSequence();  // 오늘 날짜에 대한 시퀀스를 증가시킴
    return getCurrentOrderSequence();  // 증가된 시퀀스를 반환
}

    }