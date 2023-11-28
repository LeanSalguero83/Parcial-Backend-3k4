package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = Shipper.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shipper {
    public static final String TABLE_NAME = "Shippers";
    @Id
    @Column(name = "ShipperID")
    Integer shipperId;
    @Column(name = "CompanyName", nullable = false)
    String companyName;
    @Column(name = "Phone")
    String phone;
    @OneToMany(mappedBy = "employee")
    List<Order> orders;
}
