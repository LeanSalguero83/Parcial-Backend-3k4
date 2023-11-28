package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.OrderDto;
import com.example.ParcialSabado28.model.Order;
import com.example.ParcialSabado28.repository.CustomerRepository;
import com.example.ParcialSabado28.repository.EmployeeRepository;
import com.example.ParcialSabado28.repository.ShipperRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderMapper implements Function<OrderDto, Order> {

    CustomerRepository customerRepository;
    EmployeeRepository employeeRepository;
    ShipperRepository shipperRepository;

    @Override
    public Order apply(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setCustomer(customerRepository.findById(orderDto.getCustomerId()).orElse(null));
        newOrder.setEmployee(employeeRepository.findById(orderDto.getEmployeeId()).orElse(null));
        newOrder.setOrderDate(orderDto.getOrderDate());
        newOrder.setRequiredDate(orderDto.getRequiredDate());
        newOrder.setShippedDate(orderDto.getShippedDate());
        newOrder.setShipper(shipperRepository.findById(orderDto.getShipVia()).orElse(null));
        newOrder.setFreight(orderDto.getFreight());
        newOrder.setShipName(orderDto.getShipName());
        newOrder.setShipAddress(orderDto.getShipAddress());
        newOrder.setShipCity(orderDto.getShipCity());
        newOrder.setShipRegion(orderDto.getShipRegion());
        newOrder.setShipPostalCode(orderDto.getShipPostalCode());
        newOrder.setShipCountry(orderDto.getShipCountry());
        return newOrder;
    }
}
