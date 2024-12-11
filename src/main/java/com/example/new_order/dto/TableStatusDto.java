package com.example.new_order.dto;

public class TableStatusDto {
    private boolean tableExists;
    private boolean isOccupied;

    public TableStatusDto(boolean tableExists, boolean isOccupied) {
        this.tableExists = tableExists;
        this.isOccupied = isOccupied;
    }

    public boolean isTableExists() {
        return tableExists;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
