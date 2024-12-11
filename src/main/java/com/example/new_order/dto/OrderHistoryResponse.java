package com.example.new_order.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryResponse {
   
    private String lastOrderTime;   // 마지막 주문 시각
    private BigDecimal totalSum;    // 주문 총 금액
    private String tableNumber;     // 테이블 번호
    private String storeId;         // 매장 ID

    // 쿼리 결과를 DTO로 변환하기 위한 생성자
    public OrderHistoryResponse(BigDecimal totalAmount, Timestamp createdAt, String tableNumber, String storeId) {
        // 한국 시간대 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        // Timestamp를 한국 시간대 형식으로 변환
        this.lastOrderTime = createdAt != null ? dateFormat.format(createdAt) : null;
        // totalAmount의 소수점 처리 및 반올림
        this.totalSum = totalAmount != null ? totalAmount.setScale(0, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;
        this.tableNumber = tableNumber;
        this.storeId = storeId;
    }
}
