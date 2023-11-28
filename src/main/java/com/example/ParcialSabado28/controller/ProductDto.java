package com.example.ParcialSabado28.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Integer productId;
    String productName;
    Integer supplierId; // Reemplazado por Integer en lugar de Supplier
    Integer categoryId; // Reemplazado por Integer en lugar de Category
    String quantityPerUnit;
    Double unitPrice;
    Integer unitsInStock;
    Integer unitsOnOrder;
    Integer reorderLevel;
    String discontinued;

    public void setUnitPrice(Double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("UnitPrice debe ser mayor o igual a 0");
        }
        this.unitPrice = unitPrice;
    }

    public void setReorderLevel(Integer reorderLevel) {
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("ReorderLevel debe ser mayor o igual a 0");
        }
        this.reorderLevel = reorderLevel;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        if (unitsInStock < 0) {
            throw new IllegalArgumentException("UnitsInStock debe ser mayor o igual a 0");
        }
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        if (unitsOnOrder < 0) {
            throw new IllegalArgumentException("UnitsOnOrder debe ser mayor o igual a 0");
        }
        this.unitsOnOrder = unitsOnOrder;
    }
}
