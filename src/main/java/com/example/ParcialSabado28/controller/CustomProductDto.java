package com.example.ParcialSabado28.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomProductDto {
    private Integer productId;
    private String productName;
    private Integer stockFuturo;
    private Double unitPrice;
}