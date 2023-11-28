package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDetailDto;
import com.example.ParcialSabado28.controller.OrderDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.repository.CustomerRepository;
import com.example.ParcialSabado28.repository.EmployeeRepository;
import com.example.ParcialSabado28.repository.ShipperRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDtoMapper implements Function<Order, OrderDto> {

    CustomerRepository customerRepository;
    EmployeeRepository employeeRepository;
    ShipperRepository shipperRepository;
    OrderDetailDtoMapper orderDetailDtoMapper;


    @Override
    public OrderDto apply(Order order) {
        OrderDto newOrderDto = new OrderDto();
        newOrderDto.setOrderId(order.getOrderId());
        newOrderDto.setCustomerId(order.getCustomer().getCustomerId());
        newOrderDto.setEmployeeId(order.getEmployee().getEmployeeId());
        newOrderDto.setOrderDate(order.getOrderDate());
        newOrderDto.setRequiredDate(order.getRequiredDate());
        newOrderDto.setShippedDate(order.getShippedDate());
        newOrderDto.setShipVia(order.getShipper().getShipperId());
        newOrderDto.setFreight(order.getFreight());
        newOrderDto.setShipName(order.getShipName());
        newOrderDto.setShipAddress(order.getShipAddress());
        newOrderDto.setShipCity(order.getShipCity());
        newOrderDto.setShipRegion(order.getShipRegion());
        newOrderDto.setShipPostalCode(order.getShipPostalCode());
        newOrderDto.setShipCountry(order.getShipCountry());
        List<OrderDetailDto> orderDetailDtos = order.getOrderDetails().stream()
                .map(orderDetailDtoMapper)
                .collect(Collectors.toList());
        newOrderDto.setOrderDetails(orderDetailDtos);
        return newOrderDto;
    }
}
