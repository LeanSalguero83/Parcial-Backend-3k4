package com.example.ParcialSabado28.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = CustomerDemographic.TABLE_NAME)
public class CustomerDemographic {
    public static final String TABLE_NAME = "CustomerDemographics";

    @Id
    @Column(name = "CustomerTypeID")
    private String customerTypeId;

    @Column(name = "CustomerDesc")
    private String customerDesc;

}
