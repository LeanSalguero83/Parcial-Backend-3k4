package com.example.ParcialSabado28.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerCustomerDemoId implements Serializable {

    private String customerId;
    private String customerTypeId;

    public CustomerCustomerDemoId() {
    }

    public CustomerCustomerDemoId(String customerId, String customerTypeId) {
        this.customerId = customerId;
        this.customerTypeId = customerTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCustomerDemoId that = (CustomerCustomerDemoId) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerTypeId, that.customerTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerTypeId);
    }
}