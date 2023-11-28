package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.model.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCompanyName(customer.getCompanyName());
        customerDto.setContactName(customer.getContactName());
        customerDto.setContactTitle(customer.getContactTitle());
        customerDto.setAddress(customer.getAddress());
        customerDto.setCity(customer.getCity());
        customerDto.setRegion(customer.getRegion());
        customerDto.setPostalCode(customer.getPostalCode());
        customerDto.setCountry(customer.getCountry());
        customerDto.setPhone(customer.getPhone());
        customerDto.setFax(customer.getFax());

        return customerDto;
    }
}
