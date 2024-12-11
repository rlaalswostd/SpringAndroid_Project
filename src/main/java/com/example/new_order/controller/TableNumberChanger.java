package com.example.new_order.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.new_order.dto.TableStatusDto;
import com.example.new_order.service.TableService;

@RestController
@RequestMapping("/api/tables")
public class TableNumberChanger {

    private final TableService tableService;


    public TableNumberChanger(TableService tableService) {
        this.tableService = tableService;
    }

 @GetMapping("/exists")
    public ResponseEntity<Boolean> checkTableExists(
        @RequestParam String storeId, 
        @RequestParam String tableNumber
    ) {
        boolean exists = tableService.checkTableExists(storeId, tableNumber);
        return ResponseEntity.ok(exists);
    }

  @GetMapping("/availability")
    public ResponseEntity<TableStatusDto> checkTableAvailability(
            @RequestParam String storeId,
            @RequestParam String tableNumber) {
        
        Optional<TableStatusDto> tableStatus = tableService.getTableStatus(storeId, tableNumber);

        if (tableStatus.isPresent()) {
            return ResponseEntity.ok(tableStatus.get());
        } else {
            return ResponseEntity.status(404).body(new TableStatusDto(false, false));
        }
    }

}
