package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    CustomerDto deleteCustomerById(String customerId);

    CustomerDto findCustomerById(String customerId);





}
