package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = Product.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    public static final String TABLE_NAME = "Products";
    @Id
    @Column(name = "ProductID")
    Integer productId;
    @Column(name = "ProductName", nullable = false)
    String productName;
    @ManyToOne
    @JoinColumn(name = "SupplierID")
    Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    Category category;
    @Column(name = "QuantityPerUnit")
    String quantityPerUnit;
    @Column(name = "UnitPrice" )
    Double unitPrice = 0.0;
    @Column(name = "UnitsInStock")
    Integer unitsInStock = 0;
    @Column(name = "UnitsOnOrder")
    Integer unitsOnOrder = 0;
    @Column(name = "ReorderLevel")
    Integer reorderLevel = 0;
    @Column(name = "Discontinued", nullable = false)
    String discontinued = "0";
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
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
