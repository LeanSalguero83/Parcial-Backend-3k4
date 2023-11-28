package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = Supplier.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier {
    public static final String TABLE_NAME = "Suppliers";
    @Id
    @Column(name = "SupplierID")
    Integer supplierId;
    @Column(name = "CompanyName", nullable = false)
    String companyName;
    @Column(name = "ContactName")
    String contactName;
    @Column(name = "ContactTitle")
    String contactTitle;
    @Column(name = "Address")
    String address;
    @Column(name = "City")
    String city;
    @Column(name = "Region")
    String region;
    @Column(name = "PostalCode")
    String postalCode;
    @Column(name = "Country")
    String country;
    @Column(name = "Phone")
    String phone;
    @Column(name = "Fax")
    String fax;
    @Column(name = "HomePage")
    String homePage;
    @OneToMany(mappedBy = "supplier")
    List<Product> products;
}
