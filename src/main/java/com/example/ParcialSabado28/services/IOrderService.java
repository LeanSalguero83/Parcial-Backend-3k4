package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CreateOrderRequestDto;
import com.example.ParcialSabado28.controller.OrderDto;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getAllOrders();

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    OrderDto deleteOrderById(Integer orderId);

    OrderDto findOrderById(Integer orderId);

    OrderDto createCustomOrder(CreateOrderRequestDto createOrderRequestDto);
}
