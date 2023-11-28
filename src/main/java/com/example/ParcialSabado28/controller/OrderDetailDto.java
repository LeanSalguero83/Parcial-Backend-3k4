package com.example.ParcialSabado28.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private OrderDetailIdDto id;
//    private Integer orderId;
//    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;


}
