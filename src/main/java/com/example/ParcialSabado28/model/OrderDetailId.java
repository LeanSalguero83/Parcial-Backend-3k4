package com.example.ParcialSabado28.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class OrderDetailId implements Serializable {
    private Integer orderId;
    private Integer productId;
    public OrderDetailId() {
    }

    public OrderDetailId(Integer orderId, Integer productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
