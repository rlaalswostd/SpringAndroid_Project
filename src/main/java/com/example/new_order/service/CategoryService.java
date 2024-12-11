package com.example.new_order.service;

import com.example.new_order.entity.Category;
import com.example.new_order.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesByStore(String storeId) {
        return categoryRepository.findByStoreId(storeId);  // Repository에서 카테고리 목록을 조회
    }
}
