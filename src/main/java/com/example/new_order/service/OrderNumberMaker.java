package com.example.new_order.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberMaker {
    
     private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger orderCount = new AtomicInteger(0);
    private static String currentDate = "";

    public static String generateOrderNumber() {
        String today = LocalDate.now().format(dateFormatter);

        // 날짜가 바뀌었으면 카운트를 리셋합니다.
        if (!today.equals(currentDate)) {
            currentDate = today;
            orderCount.set(0); // 주문 카운트 초기화
        }

        // 주문 번호 생성
        int orderId = orderCount.incrementAndGet(); // 카운트를 증가시킴
        return String.format("%s-%03d", currentDate, orderId); // "20241105-001" 형식
    }
}
