package com.example.ParcialSabado28.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Integer orderId;
    String customerId; // Reemplazado por String en lugar de Customer
    Integer employeeId; // Reemplazado por Integer en lugar de Employee
    String orderDate;
    String requiredDate;
    String shippedDate;
    Integer shipVia; // Reemplazado por Integer en lugar de Shipper
    Double freight;
    String shipName;
    String shipAddress;
    String shipCity;
    String shipRegion;
    String shipPostalCode;
    String shipCountry;
    Integer totalQuantity;
    List<OrderDetailDto> orderDetails;


}