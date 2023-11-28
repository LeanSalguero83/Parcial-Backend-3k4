package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.model.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CustomerMapper implements Function<CustomerDto, Customer> {
    @Override
    public Customer apply(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCompanyName(customerDto.getCompanyName());
        customer.setContactName(customerDto.getContactName());
        customer.setContactTitle(customerDto.getContactTitle());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setRegion(customerDto.getRegion());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setCountry(customerDto.getCountry());
        customer.setPhone(customerDto.getPhone());
        customer.setFax(customerDto.getFax());

        return customer;
    }
}
