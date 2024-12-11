package com.example.new_order.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.new_order.dto.TableStatusDto;
import com.example.new_order.entity.Tables;
import com.example.new_order.repository.TablesRepository;

@Service
public class TableService {
    
    private final TablesRepository tablesRepository;

    public TableService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public boolean checkTableExists(String storeId, String tableNumber) {
        return tablesRepository.existsByStoreStoreIdAndTableNumber(storeId, tableNumber);
    }

    public Optional<TableStatusDto> getTableStatus(String storeId, String tableNumber) {
        Optional<Tables> tableOptional = tablesRepository.findByStoreStoreIdAndTableNumber(storeId, tableNumber);

        if (tableOptional.isPresent()) {
            Tables table = tableOptional.get();
            boolean isOccupied = table.getIsOccupied();  // isOccupied 
            return Optional.of(new TableStatusDto(true, isOccupied));
        } else {
            return Optional.of(new TableStatusDto(false, false));
        }
    }


}
