package com.example.ParcialSabado28.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = Order.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    public static final String TABLE_NAME = "Orders";
    @Id
    @Column(name = "OrderID")
    Integer orderId;
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    Customer customer;
    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    Employee employee;
    @Column(name = "OrderDate")
    String orderDate;
    @Column(name = "RequiredDate")
    String requiredDate;
    @Column(name = "ShippedDate")
    String shippedDate;
    @ManyToOne
    @JoinColumn(name = "ShipVia")
    Shipper shipper;
    @Column(name = "Freight")
    Double freight = 0.0;
    @Column(name = "ShipName")
    String shipName;
    @Column(name = "ShipAddress")
    String shipAddress;
    @Column(name = "ShipCity")
    String shipCity;
    @Column(name = "ShipRegion")
    String shipRegion;
    @Column(name = "ShipPostalCode")
    String shipPostalCode;
    @Column(name = "ShipCountry")
    String shipCountry;
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
