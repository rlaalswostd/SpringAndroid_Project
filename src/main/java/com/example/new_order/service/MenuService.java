package com.example.new_order.service;

import com.example.new_order.entity.Menu;
import com.example.new_order.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

        //실제로 사용하는 스토어별 메뉴로드
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenuListByStoreAndCategory(String storeId, Integer categoryId) {
        return menuRepository.findByStoreIdAndCategory_CategoryId(storeId, categoryId);
    }

    // 모든 메뉴를 반환하는 메서드
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }
}
