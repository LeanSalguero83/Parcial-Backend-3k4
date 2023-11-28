package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = OrderDetail.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    public static final String TABLE_NAME = "Order Details";
    @EmbeddedId
    private OrderDetailId id;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    private Product product;
    @Column(name = "UnitPrice")
    Double unitPrice = 0.0;
    @Column(name = "Quantity")
    Integer quantity = 1;
    @Column(name = "Discount")
    Double discount = 0.0;

    public void setUnitPrice(Double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("UnitPrice debe ser mayor o igual a 0");
        }
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity debe ser mayor que 0");
        }
        this.quantity = quantity;
    }

    public void setDiscount(Double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount debe estar entre 0 y 1");
        }
        this.discount = discount;
    }
}
