package com.example.new_order.controller;

import com.example.new_order.entity.Category;
import com.example.new_order.entity.Menu;
import com.example.new_order.service.MenuService;
import com.example.new_order.repository.CategoryRepository;
import com.example.new_order.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryRepository categoryRepository;

     @Autowired
    private MenuRepository menuRepository;  // 수정된 리포지토리 사용

    @GetMapping("/menus/store/{storeId}/category/{categoryId}")
    public List<Menu> getMenuListByStoreAndCategory(
            @PathVariable String storeId,
            @PathVariable Integer categoryId) {
                System.out.println("storeId: " + storeId + ", categoryId: " + categoryId);
        // 매장 ID와 카테고리 ID에 해당하는 메뉴 목록 반환
        return menuRepository.findByStoreIdAndCategory_CategoryId(storeId, categoryId);
        
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/menus/categories")
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();  // 카테고리 목록 반환
    }
}
