package com.example.new_order.controller;

import java.util.List;

import com.example.new_order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.new_order.entity.Category;

@RestController
@RequestMapping("/api")
public class CategoryEleController {

    private final CategoryService categoryService;


    @Autowired
    public CategoryEleController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/{storeId}")
    public List<Category> getCategoriesByStore(@PathVariable String storeId) {
        // storeId를 String으로 변환
        return categoryService.getCategoriesByStore(storeId);
    }
}
