package com.example.new_order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.new_order.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
   
    List<Category> findByStoreId(String storeId);
}
