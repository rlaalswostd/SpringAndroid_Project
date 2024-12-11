package com.example.new_order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.new_order.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByStoreIdAndCategory_CategoryId(String storeId, Integer categoryId);


}