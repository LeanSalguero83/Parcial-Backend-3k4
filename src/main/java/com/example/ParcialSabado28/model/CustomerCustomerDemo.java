package com.example.ParcialSabado28.model;

import jakarta.persistence.*;

@Entity
@Table(name = CustomerCustomerDemo.TABLE_NAME)
public class CustomerCustomerDemo {
    public static final String TABLE_NAME = "CustomerCustomerDemo";

    @EmbeddedId
    private CustomerCustomerDemoId id;

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CustomerTypeID", referencedColumnName = "CustomerTypeID", insertable = false, updatable = false)
    private CustomerDemographic customerDemographic;

}
