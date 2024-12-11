package com.example.new_order.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.new_order.dto.OrderRequest;
import com.example.new_order.entity.Menu;
import com.example.new_order.entity.OrderItem;
import com.example.new_order.entity.Orders;
import com.example.new_order.entity.Tables;
import com.example.new_order.repository.MenuRepository;
import com.example.new_order.repository.OrderItemRepository;
import com.example.new_order.repository.OrdersRepository;
import com.example.new_order.repository.TablesRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;
    private final MenuRepository menuRepository;
    private final TablesRepository tablesRepository;  
    private final StoreService storeService;  
     
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, OrdersRepository ordersRepository, MenuRepository menuRepository,
    TablesRepository tablesRepository, StoreService storeService) {
        this.orderItemRepository = orderItemRepository;
        this.ordersRepository = ordersRepository;
        this.menuRepository = menuRepository;
        this.tablesRepository = tablesRepository;  
        this.storeService = storeService;  
    }

@Transactional
public void createOrder(OrderRequest orderRequest) {

   // 로그 추가: OrderRequest 필드 값 출력
   System.out.println("Received OrderRequest:");
   System.out.println("Table ID: " + orderRequest.getTableNumber());
   System.out.println("Store ID: " + orderRequest.getStoreId());
   System.out.println("Items: " + orderRequest.getItems());


    LocalDateTime localDateTime = LocalDateTime.now(); // 현재 시간을 LocalDateTime으로 얻기
     Date createdAt = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); 
        
//1. 주문번호 생성
        Long serialNumber = ordersRepository.getNextOrderSequence();  // DB 시퀀스 사용
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long orderId = Long.parseLong(date + String.format("%06d", serialNumber));// 예: 202412020101
    // 2. Orders 생성
    Orders order = new Orders();
    order.setOrderId(orderId);
    order.setStatus(Orders.OrderStatus.ORDERED);
    order.setTotalAmount(BigDecimal.ZERO);  // 초기값 0으로 설정
    order.setCreatedAt(createdAt); // 주문 생성 시점의 현재 시간을 설정
    order.setPaymentTime(null); 
    
    
    // 3. 테이블 조회 및 설정
    String tableNumber = orderRequest.getTableNumber();  // tableNumber를 변수에 저장
    String storeId = orderRequest.getStoreId();
    System.out.println("Received table Number: " + tableNumber);  // tableNumber 값 출력 테스트

    // 테이블 조회 후 없으면 새로 생성
    Tables table = tablesRepository.findByStoreStoreIdAndTableNumber(storeId,tableNumber)
    .orElseThrow(() -> {
        // 테이블 번호가 없을 경우 로그 출력
        System.out.println("Store ID: " + storeId + ", Table Number: " + tableNumber + "가 존재하지 않습니다.");
            return new IllegalArgumentException("Store ID: " + storeId + ", Table Number: " + tableNumber + "가 존재하지 않습니다.");
    });

// 테이블이 존재하면 해당 테이블을 주문에 설정
order.setTables(table);

// 주문 저장
ordersRepository.save(order);

    // 4. OrderItems 생성 및 Orders 총 금액 계산
    BigDecimal totalAmount = BigDecimal.ZERO;
    for (OrderRequest.OrderItemRequest itemRequest : orderRequest.getItems()) {
        // 메뉴 조회
        Menu menu = menuRepository.findById(itemRequest.getMenuId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid menu ID"));

        // OrderItem 생성
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenu(menu);
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setUnitPrice(menu.getPrice());
        orderItem.setRequest(itemRequest.getRequest());

        // OrderItem 저장
        orderItemRepository.save(orderItem);

        // 총 금액 계산
        totalAmount = totalAmount.add(menu.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
    }

    // 5. Orders의 총 금액 설정 및 저장
    order.setTotalAmount(totalAmount);
    System.out.println("Total Amount: " + totalAmount);  // 금액 확인
    ordersRepository.save(order);
}


}