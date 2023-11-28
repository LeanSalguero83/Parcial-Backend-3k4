package com.example.ParcialSabado28.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDto {
    private Integer supplierId;// clave para determinar productos elegible para la nueva orden
    private Integer categoryId;// clave para determinar productos elegible para la nueva orden
    private Integer stockRequired;// clave para determinar productos elegible para la nueva orden
    private String customerId;//obtenido
    private Integer employeeId;//obtenido
    private Integer shipperId;//obtenido
}
