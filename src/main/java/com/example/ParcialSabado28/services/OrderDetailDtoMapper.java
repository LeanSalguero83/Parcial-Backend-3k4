package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDetailDto;
import com.example.ParcialSabado28.controller.OrderDetailIdDto;
import com.example.ParcialSabado28.model.OrderDetail;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailDtoMapper implements Function<OrderDetail, OrderDetailDto> {


    @Override
    public OrderDetailDto apply(OrderDetail orderDetail) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setId(new OrderDetailIdDto(orderDetail.getId().getOrderId(), orderDetail.getId().getProductId()));
        dto.setUnitPrice(orderDetail.getUnitPrice());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setDiscount(orderDetail.getDiscount());
        return dto;
    }
}